package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
      System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
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
            return new BrexitTaxComputer();
         case "CN":
            return new ChinaTaxComputer();
         case "FR":
         case "ES": // other EU country codes...
         case "RO":
            return new EuropeTaxComputer();
         default:
            throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
      }
   }
}

class ChinaTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }
}

class BrexitTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }
}

class EuropeTaxComputer implements TaxComputer {
   public double compute(double tobaccoValue, double unusedRegularValue) {
      return tobaccoValue / 3;
   }

}

interface TaxComputer {
   double compute(double tobaccoValue, double regularValue);
}
