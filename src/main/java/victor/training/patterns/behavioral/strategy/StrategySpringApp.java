package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

interface TaxComputer {
   double compute(double tobaccoValue, double regularValue);

}

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
   private ConfigProvider configProvider = new ConfigFileProvider();

   public static void main(String[] args) {
      new SpringApplicationBuilder(StrategySpringApp.class)
          .profiles("localProps")
          .run(args);
   }

   // TODO [1] Break CustomsService logic into Strategies
   // TODO [2] Convert it to Chain Of Responsibility
   // TODO [3] Wire with Spring
   // TODO [4] ConfigProvider: selected based on environment props, with Spring
   public void run(String... args) {
      CustomsService service = new CustomsService();
      System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("NL", 100, 100));
      System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
      System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

      System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
   }
}

class CustomsService {
   public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
      TaxComputer computer = selectTaxComputer(originCountry);
      return computer.compute(tobaccoValue, regularValue);
   }

   private TaxComputer selectTaxComputer(String originCountry) {
      switch (originCountry) {
         case "UK":
            return new UKTaxComputer();
         case "CN":
            return new ChinaTaxComputer();
         case "FR":
         case "ES": // other EU country codes...
         case "NL":
            return new EUTaxComputer();
         default:
            throw new IllegalStateException("Unexpected value: " + originCountry);
      }
   }
}

//@Service
//class OrderService {
//   public void placeOrder(Message mesage) {
//
//   }
//
//   {
//      MessageType messageType;
////      switch(messageType) {
////         case
////      }
//      messageType.handleFunction.accept(this, message);
//   }
//}
//
//
//enum MessageType {
//   PLACE_ORDER(OrderService::placeOrder),
//   CANCEL_ORDER
//   ;
//
//   public final BiConsumer<OrderService, Message> handleFunction;
//
//   MessageType(BiConsumer<OrderService, Message> handleFunction)  {
//      this.handleFunction = handleFunction;
//   }
//}

class UKTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }

}

class ChinaTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }

}

class EUTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double regularValueUNUSED) {
      // growing
      // growing
      //brewing bugs
      return tobaccoValue / 3;
   }

}
