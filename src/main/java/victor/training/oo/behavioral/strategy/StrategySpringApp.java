package victor.training.oo.behavioral.strategy;

import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
      System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
      System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));

      System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
   }
}

//enum CountryCode {
//      UK(new UKTaxCalculator());
//      RO(new EUTaxCalculator());
//      ES(new EUTaxCalculator());
//      FR(new EUTaxCalculator());
//
//      private final UKTaxCalculator ukTaxCalculator;
//
//      CountryCode(UKTaxCalculator ukTaxCalculator) {
//
//         this.ukTaxCalculator = ukTaxCalculator;
//      }
//}

@Value
class Country {
   String countryCode;
   TaxCalculator calculator;
}

class CustomsService {

   public static final Map<String, Class<? extends TaxCalculator>> MAP = new HashMap<>();

   static {
      new Country("UK", new UKTaxCalculator());
      new Country("FR", new EUTaxCalculator());
      new Country("ES", new EUTaxCalculator());
      new Country("RO", new EUTaxCalculator());
      // indiscutabil mai bine Map daca incarci asocierea dintre ISO cod si implementation class full name din .properties
//      MAP.put("UK", UKTaxCalculator.class);
//      MAP.put("CN", ChinaTaxCalculator.class);
//      MAP.put("FR", EUTaxCalculator.class);
//      MAP.put("ES", EUTaxCalculator.class);
//      MAP.put("RO", EUTaxCalculator.class);
   }

   public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
      TaxCalculator taxCalculator = createTaxCalculator(originCountry);
      return taxCalculator.calculate(tobaccoValue, regularValue);
   }

   public TaxCalculator createTaxCalculator(String originCountry) { // UGLY API we CANNOT change
      List<TaxCalculator> calculators = Arrays.asList(new UKTaxCalculator(), new EUTaxCalculator(), new ChinaTaxCalculator());
      for (TaxCalculator calculator : calculators) {
         if (calculator.accepts(originCountry)) {
            return calculator;
         }
      }
      throw new IllegalArgumentException("Unkown country code " + originCountry);
   }
}

interface TaxCalculator {
   boolean accepts(String countryCode);

   Double calculate(double tobaccoValue, double regularValue);
}

class ChinaTaxCalculator implements TaxCalculator {
   @Override
   public boolean accepts(String countryCode) {
      return "CN".equals(countryCode);
   }
   public Double calculate(double tobaccoValue, double regularValue) {
      // mult cod
      return tobaccoValue + regularValue;
   }
}

class UKTaxCalculator implements TaxCalculator {
   @Override
   public boolean accepts(String countryCode) {
      return "UK".equals(countryCode);
   }

   public Double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue / 2 + regularValue;
   }
}

class EUTaxCalculator implements TaxCalculator {
   @Override
   public boolean accepts(String countryCode) {
      return Arrays.asList("FR","ES","RO").contains(countryCode);
   }

   public Double calculate(double tobaccoValue, double regularValue) {
      return tobaccoValue / 3;
   }

}
