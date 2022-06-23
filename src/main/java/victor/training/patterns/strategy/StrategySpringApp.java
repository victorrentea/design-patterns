package victor.training.patterns.strategy;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

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
    @Autowired
        CustomsService service = new CustomsService();
    public void run(String... args) {
        System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
        System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
        System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

        System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
    }
}

@Component
class CustomsService {

//    Map<String, Class< ? extends TaxCalculator>> calculators = Map.of(
//            "UK", BrexitTaxCalculator.class,
//            "CN", ChinaTaxCalculator.class,
//            "RO", EUTaxCalculator.class,
//            "ES", EUTaxCalculator.class,
//            "FR", EUTaxCalculator.class
//    );

    @Autowired
    List<TaxCalculator> allCalculators;

    @SneakyThrows
    public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
//        TaxCalculator calculator = switch (originCountry) {
//            case "UK" -> new BrexitTaxCalculator();
//            case "CN" -> new ChinaTaxCalculator(); // other EU country codes...
//            case "FR", "ES", "RO" -> new EUTaxCalculator();
//            default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//        };
//        TaxCalculator calculator = calculators.get(originCountry).newInstance();
        TaxCalculator calculator = allCalculators.stream()
                .filter(c -> c.match(originCountry))
                .findFirst().orElseThrow();
        return calculator.calculate(tobaccoValue, regularValue);
    }
}
interface TaxCalculator {
    double calculate(double tobaccoValue, double regularValue);
//    Set<String> getCountryCode();
    boolean match(String countryCode);
}
@Component
class ChinaTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // +50 more lines of code
        return tobaccoValue + regularValue;
    }

    @Override
    public boolean match(String countryCode) {
        return "CN".equals(countryCode); // yoda notation
    }

}

@Component
class BrexitTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // +20 more lines of code
        return tobaccoValue / 2 + regularValue;
    }

    @Override
    public boolean match(String countryCode) {
        return "UK".equals(countryCode);
    }
}

@Component
class EUTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue_NotUsed_threPriceToPayForPolymorphism) {
        // +50 more lines of code
        return tobaccoValue / 3;
    }

    @Override
    public boolean match(String countryCode) {
        return Set.of("RO","ES","FR").contains(countryCode);
    }
}