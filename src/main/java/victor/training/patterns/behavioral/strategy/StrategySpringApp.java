package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

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

   @Autowired
   CustomsService service;

   // TODO [1] Break CustomsService logic into Strategies
   // TODO [2] Convert it to Chain Of Responsibility
   // TODO [3] Wire with Spring
   // TODO [4] ConfigProvider: selected based on environment props, with Spring
   public void run(String... args) {
      System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
      System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
      System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

      System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
   }
}

@Service
class CustomsService {
   public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
      TaxCalculator taxCalculator = selectTaxCalculator(originCountry);

      return taxCalculator.calculate(tobaccoValue, regularValue);
   }

   @Autowired
   List<TaxCalculator> calculators;

//   static Map<String, TaxCalculator> calculatorsMap = loadDinFisier();
//   static {
//      calculatorsMap.put("UK", new UKTaxCalculator());
//      calculatorsMap.put("RO", new EUTaxCalculator());
//      calculatorsMap.put("ES", new EUTaxCalculator());
//   }


   private TaxCalculator selectTaxCalculator(String originCountry) {
//      return calculatorsMap.get(originCountry);

      for (TaxCalculator calculator : calculators) {
         if (calculator.isApplicableForCountry(originCountry)) {
            return calculator;
         }
      }
      throw new IllegalArgumentException(originCountry);
   }
}

@Service
class ChinaTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue + regularValue;
   }

   @Override
   public boolean isApplicableForCountry(String isoCode) {
      return "CN".equals(isoCode);
   }
}

@Service
class UKTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }

   @Override
   public boolean isApplicableForCountry(String isoCode) {
      return "UK".equals(isoCode);
   }
}

@Service
class EUTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValueUnused) {
      return tobaccoValue / 3;
   }

   @Override
   public boolean isApplicableForCountry(String isoCode) {
      return asList("RO", "ES", "FR").contains(isoCode);
   }
}

interface TaxCalculator {
   double calculate(double tobaccoValue, double regularValue);

   boolean isApplicableForCountry(String isoCode);
}
