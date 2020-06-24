package victor.training.oo.behavioral.strategy;

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

   @Autowired
   private CustomsService service;

   @Autowired
   private ConfigProvider configProvider;

   // TODO [1] Break CustomsService logic into Strategies
   // TODO [2] Convert it to Chain Of Responsibility
   // TODO [3] Wire with Spring
   // TODO [4] ConfigProvider: selected based on environment props, with Spring
   public void run(String... args) {
      System.out.println(configProvider.getProperties());
//      CustomsService service = new CustomsService();
      System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
      System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
      System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));

      System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
   }
}

@Service
class CustomsService {
   public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
      TaxCalculator calculator = selectTaxCalculator(originCountry);
      return calculator.calculate(tobaccoValue, regularValue);
   }

   private TaxCalculator selectTaxCalculator(String originCountry) {
//      return switch (originCountry) {
//         case "UK" -> new UKTaxCalculator();
//         case "CN" -> new CNTaxCalculator();
//         case "FR", "ES", "RO" -> new EUTaxCalculator();
//         default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//      };

      // cu switch + Visitor
//      switch (originCountry) {
//         case "UK": return new UKTaxCalculator();
//         case "CN": return new CNTaxCalculator();
//         case "FR":
//         case "ES": // other EU country codes...
//         case "RO": return new EUTaxCalculator();
//         default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//      }

      // chain of responsibility
//      List<TaxCalculator> calculators = Arrays.asList(new UKTaxCalculator(), new CNTaxCalculator(), new EUTaxCalculator());

      for (TaxCalculator calculator : calculators) {
         if (calculator.isApplicable(originCountry)) {
            return calculator;
         }
      }
      throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
   }
   @Autowired
   List<TaxCalculator> calculators;
//   @Autowired
//   Optional<ConfigProvider> configProvider;
}

interface TaxCalculator {
   double calculate(double tobaccoValue, double regularValue);
//   Collection<String> getCountryNames();
   boolean isApplicable(String countryName);
}
@Service
class CNTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      // Maricica inca 15 linii
      // Maricica inca 15 linii
      // Maricica inca 15 linii
      // Marcel a lasat si el aici 20 linii de cod
      return tobaccoValue + regularValue;
   }

   @Override
   public boolean isApplicable(String countryName) {
      return "CN".equals(countryName);
   }
}
@Service
class EUTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) { // loss of specificity. luam un param de-a-n boulea
      return tobaccoValue / 3;
   }

   @Override
   public boolean isApplicable(String countryName) {
      return Arrays.asList("FR","RO","ES").contains(countryName);
   }
}
@Service
class UKTaxCalculator implements TaxCalculator {
   public double calculate(double tobaccoValue, double regularValue) {
      // Marcel a lasat si el aici 20 linii de cod
      // Marcel a lasat si el aici 20 linii de cod
      // Marcel a lasat si el aici 20 linii de cod
      return tobaccoValue / 2 + regularValue;
   }

   @Override
   public boolean isApplicable(String countryName) {
      return "UK".equals(countryName);
   }
}
