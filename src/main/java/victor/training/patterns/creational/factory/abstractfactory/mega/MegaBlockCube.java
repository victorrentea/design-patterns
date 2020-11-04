package victor.training.patterns.creational.factory.abstractfactory.mega;

import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

public class MegaBlockCube implements Cube {
   @Override
   public void stackOnto(Cube anotherCube) {
      // intelligent here
   }

   @Override
   public void putOn(Board board) {

   }
}
