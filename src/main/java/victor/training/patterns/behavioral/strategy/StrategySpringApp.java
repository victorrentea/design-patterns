package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

import static java.util.Arrays.asList;

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
      TaxCalculator taxCalculator = selectTaxCalculator(originCountry);

      return taxCalculator.calculate(tobaccoValue, regularValue);
   }

   private TaxCalculator selectTaxCalculator(String originCountry) {
      List<TaxCalculator> calculators = asList(new UKTaxCalculator(), new ChinaTaxCalculator(), new EUTaxCalculator());

      for (TaxCalculator calculator : calculators) {
         if (calculator.getApplicableCountryIso().contains(originCountry)) {
            return calculator;
         }
      }
      throw new IllegalArgumentException(originCountry);
   }
}

class ChinaTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }

   public List<String> getApplicableCountryIso() {
      return asList("CN");
   }
}

class UKTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }

   public List<String> getApplicableCountryIso() {
      return asList("UK");
   }
}

class EUTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValueUnused) {
      return tobaccoValue / 3;
   }

   public List<String> getApplicableCountryIso() {
      return asList("RO", "ES", "FR");
   }
}

interface TaxCalculator {
   double calculate(double tobaccoValue, double regularValue);

   List<String> getApplicableCountryIso();
}
