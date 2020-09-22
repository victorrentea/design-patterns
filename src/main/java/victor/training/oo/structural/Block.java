package victor.training.oo.structural;

public interface Block {
    void stickIntoBoard(Board board);
}

class LegoBlock implements Block {

   @Override
   public void stickIntoBoard(Board board) {
      if (!(board instanceof LegoBoard)) {
         throw new IllegalArgumentException("Nu ma pot combina cu piese low-cost");
      }
   }
}

class MegaBlocksBlock implements Block {

   @Override
   public void stickIntoBoard(Board board) {
      if (!(board instanceof MegaBlocksBoard)) {
         throw new IllegalArgumentException("Nu ma pot combina cu piese low-cost");
      }
   }
}