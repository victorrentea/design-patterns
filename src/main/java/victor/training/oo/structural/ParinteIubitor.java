package victor.training.oo.structural;

import lombok.RequiredArgsConstructor;
import victor.training.oo.stuff.ThreadUtils;

public class ParinteIubitor {
   public static void main(String[] args) {
      Copil copil = new Copil();
      Parinte parinte = new Parinte(copil);
      parinte.vineAcasaObosit(MagazinDeJucarii.obtineJucarie(true));

//      Connection connection = DriverManager.getConnection("");
      Block block1 = new LegoFactory().getBlock();
      Board board = new MegaBlocksFactory().getBoard();
      block1.stickIntoBoard(board);

   }

}

class MagazinDeJucarii {

   public static BlockFactory obtineJucarie(boolean ieftin) {

//      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//      DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
//      Document doc = db.newDocument();
//      doc.createElement().setAttribute(doc.createAttribute("id"));


      if (ieftin) {
         return new MegaBlocksFactory(); // de 3 ori mai eftin
      } else {
         return new LegoFactory();
      }
   }
}

interface BlockFactory {
   Block getBlock();
   Board getBoard();
}



class LegoFactory implements BlockFactory{
   @Override
   public Block getBlock() {
      return new LegoBlock();
   }
   @Override
   public Board getBoard() {
      return new LegoBoard();
   }
}
class MegaBlocksFactory implements BlockFactory{
   @Override
   public Block getBlock() {
      return new MegaBlocksBlock();
   }
   @Override
   public Board getBoard() {
      return new MegaBlocksBoard();
   }
}

@RequiredArgsConstructor
class Parinte {
   private final Copil copil;
   public void vineAcasaObosit(BlockFactory blockFactory) {


      copil.daJucarie(blockFactory);
   }
}

class Copil {
   public void daJucarie(BlockFactory blockFactory) {
      System.out.println("Ma joc cu " + blockFactory);
      Block block = blockFactory.getBlock();
      Board board = blockFactory.getBoard();
      block.stickIntoBoard(board);

      Block block2 = blockFactory.getBlock();
      block2.stickIntoBoard(board);
      ThreadUtils.sleep(5000);

   }
}


