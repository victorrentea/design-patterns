package victor.training.oo.creational.factory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.System.exit;

public class FactoriesStart {
   public static void main(String[] args) throws ParserConfigurationException {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // Static Factory Method care intoarce ceva abstract
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.newDocument();

      Element a = doc.createElement("a");
      Attr attr = doc.createAttribute("href"); // abstract factory
      a.appendChild(attr);


      LovingParent parent = new LovingParent(new Child());

      parent.finishWorkExhausted();
   }
}


/// **********  Abstract Factory Design Pattern
interface JocCuCuburi {
   Cube createCube();
   Board createBoard();
}
interface Cube {
   void stackOnto(Cube other);
   void stickIn(Board board);
}
interface Board {

}
// *********************
class LegoGame implements JocCuCuburi {
   public Cube createCube() {
      return new LegoCube();
   }
   public Board createBoard() {
      return new LegoBoard();
   }
}
class LegoCube implements Cube{
   public void stackOnto(Cube other) {
      if (!(other instanceof LegoCube)) {
         throw new IllegalArgumentException("Nu ma pot combina cu alte cuburi low-cost");
      }
   }
   public void stickIn(Board board) {
   }
}
class LegoBoard implements  Board {}

/// ***********************
class MegaBlocksGame implements JocCuCuburi {
   public Cube createCube() {
      return new MegaBlocksCube();
   }
   public Board createBoard() {
      return new MegaBlocksBoard();
   }
}

class MegaBlocksCube implements Cube {
   public void stackOnto(Cube other) {
   }
   public void stickIn(Board board) {
   }
}
class MegaBlocksBoard implements  Board {

}


// ******************

class MagazinDeJucarii {
   static int legoStock = 1;

   public static JocCuCuburi cumparaJucarie(boolean scumpa) {
      if (scumpa) {
         return new LegoGame();
      } else {
         return new MegaBlocksGame();
      }
   }
}

class LovingParent {
   private final Child child;

   public LovingParent(Child child) {
      this.child = child;
   }

   public void finishWorkExhausted() {
      JocCuCuburi joc = MagazinDeJucarii.cumparaJucarie(false); // sotia observa ca Lego a costat 30 EUR
      child.play(joc);
      child.noticeAndKillParent();
   }
}

class Child {

   public void noticeAndKillParent() {
      System.err.println("Drain parent blood");
      exit(-1);
   }

   public void play(JocCuCuburi joc) {
      System.out.println("Ma joc cu " + joc);
      Cube cube = joc.createCube();
      Cube cube2 = joc.createCube();
      cube.stackOnto(cube2);

      Board board = joc.createBoard();
      cube.stickIn(board);

//      ThreadUtils.sleepq(5000);


      Cube legoCube = new LegoGame().createCube();
      Cube megaCube = new MegaBlocksGame().createCube();

      legoCube.stackOnto(megaCube);

   }
}
