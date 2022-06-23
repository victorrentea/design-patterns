package victor.training.patterns.strategy;

import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;
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
    public void run(String... args) {
        CustomsService service = new CustomsService();
        System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
        System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
        System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

        System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
    }
}

class CustomsService {

//    Map<String, Class< ? extends TaxCalculator>> calculators = Map.of(
//            "UK", BrexitTaxCalculator.class,
//            "CN", ChinaTaxCalculator.class,
//            "RO", EUTaxCalculator.class,
//            "ES", EUTaxCalculator.class,
//            "FR", EUTaxCalculator.class
//    );
    @SneakyThrows
    public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
        TaxCalculator calculator = switch (originCountry) {
            case "UK" -> new BrexitTaxCalculator();
            case "CN" -> new ChinaTaxCalculator(); // other EU country codes...
            case "FR", "ES", "RO" -> new EUTaxCalculator();
            default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
        };
//        TaxCalculator calculator = calculators.get(originCountry).newInstance();
        return calculator.calculate(tobaccoValue, regularValue);
    }
}
interface TaxCalculator {
    double calculate(double tobaccoValue, double regularValue);
}
class ChinaTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // +50 more lines of code
        return tobaccoValue + regularValue;
    }
}

class BrexitTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // +20 more lines of code
        return tobaccoValue / 2 + regularValue;
    }
}

class EUTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue_NotUsed_threPriceToPayForPolymorphism) {
        // +50 more lines of code
        return tobaccoValue / 3;
    }
}