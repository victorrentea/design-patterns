package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(StrategySpringApp.class)
                .profiles("localProps")
                .run(args);
    }

    private ConfigProvider configProvider = new ConfigFileProvider();

    @Autowired
    private CustomsService service;

    // TODO [1] Break CustomsService logic into Strategies
    // TODO [2] Convert it to Chain Of Responsibility
    // TODO [3] Wire with Spring
    public void run(String... args) {
        System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
        System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
        System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

        System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
    }
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
    //	private Map<String, TaxCalculator> calculators;  // hehe

    public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
        TaxCalculator taxCalculator = selectCalculatorFor(originCountry);
        return taxCalculator.calculate(tobaccoValue, regularValue);
    }

    private TaxCalculator selectCalculatorFor(String originCountry) {
        switch (originCountry) { // every clean switch lives alone in its method
            case "UK":
                return new UKTaxCalculator();
            case "CN":
                return new ChinaTaxCalculator();
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return new EUTaxCalculator();
            default: // WHY? is this here?
                throw new IllegalArgumentException(
                        "out of HOPE: it will never pop. because I will be carefu" +
                       "l to add a case in every switch (originCountry) = runtime risk " +
                       "Not a valid country ISO2 code: " + originCountry);
        }
    }

}

interface TaxCalculator {
    double calculate(double tobaccoValue, double regularValue);
}
@Component
class UKTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // imagine dragons...
        return tobaccoValue / 2 + regularValue;
    }
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        return tobaccoValue + regularValue;
    }
}
@Component
class EUTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValueUseless) { // a bit of a loss: democracy = the tyranny of majority
        return tobaccoValue / 3;
    }

}