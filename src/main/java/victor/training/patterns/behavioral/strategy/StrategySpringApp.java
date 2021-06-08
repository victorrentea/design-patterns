package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
      List<TaxComputer> allComputers = Arrays.asList(new ChinaTaxComputer(), new EuropeTaxComputer(), new BrexitTaxComputer());
      // you are allowed to change the interface.
      for (TaxComputer computer : allComputers) {
         if (computer.supportsCountry(originCountry)) {
            return computer;
         }
      }
      throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
   }
}

@Service
class ChinaTaxComputer implements TaxComputer {
   @Override
   public boolean supportsCountry(String country) {
      return "CN".equals(country);
   }

   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }
}

@Service
class BrexitTaxComputer implements TaxComputer {
   @Override
   public boolean supportsCountry(String country) {
      return "UK".equals(country);
   }

   public double compute(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }
}

@Service
class EuropeTaxComputer implements TaxComputer {
   @Override
   public boolean supportsCountry(String country) {
      return Arrays.asList("RO", "ES", "FR").contains(country);
   }

   public double compute(double tobaccoValue, double unusedRegularValue) {
      return tobaccoValue / 3;
   }

}

interface TaxComputer {
   boolean supportsCountry(String country);

   double compute(double tobaccoValue, double regularValue);
}
