package victor.training.oo.creational.abstractfactory.blocks.megablock;

import victor.training.oo.creational.abstractfactory.blocks.spi.BlockFactory;
import victor.training.oo.creational.abstractfactory.blocks.spi.Board;
import victor.training.oo.creational.abstractfactory.blocks.spi.Cube;

// 30 lei
public class MegaBlockBox implements BlockFactory {
   @Override
   public Board createBoard() {
      return new MegaBlockBoard();
   }

   @Override
   public Cube createCube() {
      return new MegaBlockCube();
   }
}
