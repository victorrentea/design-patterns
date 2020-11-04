package victor.training.patterns.creational.factory;

import victor.training.patterns.creational.factory.abstractfactory.lego.LegoFactory;
import victor.training.patterns.creational.factory.abstractfactory.mega.MegaBlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;
import victor.training.patterns.stuff.ThreadUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.System.exit;

public class FactoriesStart {
   public static void main(String[] args) {
      LovingParent parent = new LovingParent(new Child());
      parent.finishWorkExhausted();

      ArrayList<Object> list = new ArrayList<>();

      List<Object> notChangeable = Collections.unmodifiableList(list);
   }
}


class LovingParent {
//   private boolean loving = true;
//   private Love love
//   Loving part of LovingParent be a field.

   private final Child child;

   public LovingParent(Child child) {
      this.child = child;
   }

   public void finishWorkExhausted() {
//      Toy toy = ToyShop.buyToy();
//      child.play(toy);
//      child.playWithConstructionGame(new LegoFactory()); // costs = 25 EUR
      child.playWithConstructionGame(new MegaBlockFactory()); // costs = 10 EUR
      child.noticeAndDrainParent();
   }
}

class ToyShop { // close by
   public static Toy buyToy() {
//      return new Plasticine();
      return new ToyHammer();
   }
}

class Child {

   public void noticeAndDrainParent() {
      System.out.println("Drain parent energy");
      exit(-1);
   }

   public void play(Toy toy) {
      Objects.requireNonNull(toy, "Kid too young, unable to play alone. Try a toy!");
      System.out.println("Playing with " + toy + " ...");
      toy.use();
      ThreadUtils.sleepq(1000);
      System.out.println("Done Playing");
   }

   public void playWithConstructionGame(BlockFactory legoGame) {
      Board board = legoGame.createBoard();
      System.out.println("I am putting the first block on the  " + board);
      Cube previousCube = legoGame.createCube();
      previousCube.putOn(board);
      for (int i = 0; i< 10;i++) {
         Cube nextCube = legoGame.createCube();
         System.out.println("I am stacking " + nextCube + " on top of " + previousCube);
         nextCube.stackOnto(previousCube);
         previousCube = nextCube;
      }

      Cube legoCube = new LegoFactory().createCube();
      Cube megaCube = new MegaBlockFactory().createCube();
      legoCube.stackOnto(megaCube);

   }
}
interface Toy {
   void use();
}
class ToyHammer implements Toy {
   public void use() {
      System.out.println("Bang-Bang!");
   }
}

class Plasticine implements Toy{
   public void use() {
      System.out.println("Model some stuff");
   }
}