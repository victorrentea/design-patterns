package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
   public static void main(String[] args) {
      new SpringApplicationBuilder(StrategySpringApp.class)
          .profiles("localProps")
          .run(args);
   }


   private ConfigProvider configProvider = new ConfigFileProvider();

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
      TaxCalculator taxCalculator = selectTaxCalculator(originCountry);
      return taxCalculator.calculate(tobaccoValue, regularValue);
   }

   private TaxCalculator selectTaxCalculator(String originCountry) {
      List<TaxCalculator> calculators = asList(new UKTaxCalculator(), new EUTaxCalculator(), new ChinaTaxCalculator());

      for (TaxCalculator calculator : calculators) {
         if (calculator.canProcess(originCountry)) {
            return calculator;
         }
      }
      throw new IllegalStateException("Unexpected value: " + originCountry);

   }
}

interface TaxCalculator {
   double calculate(double tobaccoValue, double regularValue);

   boolean canProcess(String originCountry);
}


class UKTaxCalculator implements TaxCalculator {
   @Override
   public double calculate(double tobaccoValue, double regularValue) {
      // Maria: las si eu aicea o sacosa  de cod
      return tobaccoValue / 2 + regularValue;
   }

   @Override
   public boolean canProcess(String originCountry) {
      return "UK".equals(originCountry);
   }
}

class ChinaTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }

   @Override
   public boolean canProcess(String originCountry) {
      return "CN".equals(originCountry);
   }
}

class EUTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValueDegeaba) {
      return tobaccoValue / 3;
   }

   @Override
   public boolean canProcess(String originCountry) {
      return asList("RO", "ES", "FR").contains(originCountry);
   }

}