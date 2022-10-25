package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

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
        var taxCalculator = selectCalculatorFor(originCountry);
        return taxCalculator.apply(tobaccoValue, regularValue);
    }

    private BiFunction<Double,Double, Double> selectCalculatorFor(String originCountry) {
        switch (originCountry) { // every clean switch lives alone in its method
            case "UK":
                return (t,r) -> t / 2 + r;
            case "CN":
                return (t,r) -> t + r;
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return (t,r) -> t/3;
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
        }
    }

}
