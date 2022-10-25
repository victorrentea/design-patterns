package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
        switch (originCountry) {
            case "UK":
                return new UKTaxCalculator().calculateUKTax(tobaccoValue, regularValue);
            case "CN":
                return new ChinaTaxCalculator().calculateChinaTax(tobaccoValue, regularValue);
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return new EUTaxCalculator().calculateEUTax(tobaccoValue);
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
        }
    }

}


class UKTaxCalculator {
    public double calculateUKTax(double tobaccoValue, double regularValue) {
        // imagine dragons...
        return tobaccoValue / 2 + regularValue;
    }
}

class ChinaTaxCalculator {
    public double calculateChinaTax(double tobaccoValue, double regularValue) {
        return tobaccoValue + regularValue;
    }
}

class EUTaxCalculator {
    public double calculateEUTax(double tobaccoValue) {
        return tobaccoValue / 3;
    }

}