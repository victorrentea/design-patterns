package victor.training.oo.creational.factory;

import java.math.BigDecimal;

public class InstanceFactoryMethod {
   public static void main(String[] args) {

      BigDecimal bd = BigDecimal.ONE;

      BigDecimal two = bd.add(bd);
   }
}
