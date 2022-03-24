package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
   @Autowired
   CustomsService service;

   public void run(String... args) {
      System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
      System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
      System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

      System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
   }
}

@Service
class CustomsService {
   @Autowired
   List<TaxCalculator> all;

   public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
      TaxCalculator calculator = selectCalculator(originCountry);
      return calculator.calculateTaxes(tobaccoValue, regularValue);
   }


   private TaxCalculator selectCalculator(String originCountry) {
      return all.stream()
          .filter(calculator -> calculator.supports(originCountry))
          .findFirst()
          .orElse(new DefaultTaxCalculator());
   }
}

interface TaxCalculator {
   double calculateTaxes(double tobaccoValue, double regularValue);

   //   Set<String> getCountries(); // no need to expose the set, JUST ASK if the country IS SUPPORTED!!!
   boolean supports(String originCountry);
}

@Service
class ChinaTaxCalculator implements TaxCalculator {
   public double calculateTaxes(double tobaccoValue, double regularValue) {
      // LOGIC
      return tobaccoValue + regularValue;
   }

   @Override
   public boolean supports(String originCountry) {
      return "CN".equals(originCountry);
   }
}

@Service
class UKTaxCalculator implements TaxCalculator {
   public double calculateTaxes(double tobaccoValue, double regularValue) {
      // LOGIC
      return tobaccoValue / 2 + regularValue;
   }

   @Override
   public boolean supports(String originCountry) {
      return "UK".equals(originCountry);
   }
}

//
//enum Country {
//   RO(EUTaxCalculator.class),
//   FR(EUTaxCalculator.class),
//   UK(UKTaxCalculator.class);
//
//   Country(Class<EUTaxCalculator> euTaxCalculatorClass) {
//
//   }
//}
//@Order(1)
@Service
class EUTaxCalculator implements TaxCalculator {
   public double calculateTaxes(double tobaccoValue, double regularValue_useless_param) {
      return tobaccoValue / 3;
   }

   @Override
   public boolean supports(String originCountry) {
      return Arrays.asList("ES", "FR", "RO").contains(originCountry);
   }
}

class DefaultTaxCalculator implements TaxCalculator {
   public double calculateTaxes(double tobaccoValue, double regularValue_useless_param) {
      return 0;
   }

   @Override
   public boolean supports(String originCountry) {
      return true;
   }
}