package victor.training.patterns.creational.factory.abstractfactory.mega;

import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

public class MegablocksFactory implements BlockFactory {
   @Override
   public Board createBoard() {
      return new MegablocksBoard();
   }

   @Override
   public Cube createCube() {
      return new MegablocksCube();
   }
}
