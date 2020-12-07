package victor.training.patterns.creational.factory.abstractfactory.mega;

import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

public class MegaBlocksBox implements BlockFactory {
   @Override
   public Board createBoard() {
      return new MegaBlocksBoard();
   }

   @Override
   public Cube createCube() {
      return new MegaBlocksCube();
   }
}
