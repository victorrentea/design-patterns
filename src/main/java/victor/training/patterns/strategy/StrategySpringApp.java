package victor.training.patterns.strategy;

import lombok.Data;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static victor.training.patterns.strategy.Country.UK;

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


enum Country {
    UK(UKTaxCalculator.class),
    CN(ChinaTaxCalculator.class),
    FR(EUTaxCalculator.class),
    ES(EUTaxCalculator.class),
    RO(EUTaxCalculator.class);

    public final Class<? extends TaxCalculator> calculatorClass;
//    public final BiFunction<....> calculatorFunction;

    Country(Class<? extends TaxCalculator> calculatorClass) {
        this.calculatorClass = calculatorClass;
    }
}
@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
    // externalize the map into a config file
//    private Map<Country, Class<? extends TaxCalculator>> calculators;

    public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
        TaxCalculator taxCalculator = selectCalculatorFor(originCountry, LocalDate.now());
        return taxCalculator.calculate(tobaccoValue, regularValue);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<TaxCalculator> taxCalculators;

    private TaxCalculator selectCalculatorFor(String originCountry, LocalDate parcelDate) {
        for (TaxCalculator taxCalculator : taxCalculators) {
            if (taxCalculator.canHandle(originCountry, parcelDate)) {
                return taxCalculator;
            }
        }
        throw new IllegalArgumentException("Not supported");
    }
        // TODO CR: the taxes for China have changed starting 1 Nov 2022.
        //   => the selection criteria of the strategy to use is COMPLEX
//        return switch (originCountry) { // every clean switch lives alone in its method
//            case "UK" -> new UKTaxCalculator();
//            case "CN" -> {
//                // how can I avoid piling up all (growin complex) decisions about WHAT strategy to use for a
//                // given parcel.
//                if (parcelDate.isBefore(LocalDate.parse("2022-11-01")))
//                    yield new ChinaTaxCalculator(); // other EU country codes...
//                else yield new ChinaTaxCalculatorAfterNov2022();
//
//            }
//            case "FR", "ES", "RO" -> new EUTaxCalculator();
//            default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//        };
//    }


//        Class<? extends TaxCalculator> calculatorClass = calculators.get(originCountry);
//        return applicationContext.getBean(calculatorClass);

//        return applicationContext.getBean(originCountry.calculatorClass);

//        return switch (originCountry) { // every clean switch lives alone in its method
//            case UK -> new UKTaxCalculator();
//            case CN -> new ChinaTaxCalculator(); // other EU country codes...
//            case FR, ES, RO -> new EUTaxCalculator();
//        };


}

interface TaxCalculator {
    boolean canHandle(String originCountry, LocalDate parcelDate);
    double calculate(double tobaccoValue, double regularValue);
}

@Component
class UKTaxCalculator implements TaxCalculator {

    @Override
    public boolean canHandle(String originCountry, LocalDate parcelDate) {
        return "UK".equals(originCountry);
    }

    public double calculate(double tobaccoValue, double regularValue) {
        // imagine dragons...
        return tobaccoValue / 2 + regularValue;
    }
}

@Component
class ChinaTaxCalculatorAfterNov2022 implements TaxCalculator {
    @Override
    public boolean canHandle(String originCountry, LocalDate parcelDate) {
        return "CN".equals(originCountry) && parcelDate.isAfter(LocalDate.parse("2022-11-01"));
    }

    public double calculate(double tobaccoValue, double regularValue/*, LocalDate parcelDate*/) {
//        if (parcelDate.isAfter(LocalDate.parse("2022-11-01"))) {
            return tobaccoValue + regularValue + 1;
//        } else {
//            return new ChinaTaxCalculator().calculate(tobaccoValue, regularValue);
//        }
    }
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
    @Override
    public boolean canHandle(String originCountry, LocalDate parcelDate) {
        return "CN".equals(originCountry) && !parcelDate.isAfter(LocalDate.parse("2022-11-01"));
    }
    public double calculate(double tobaccoValue, double regularValue) {
        return tobaccoValue + regularValue;
    }
}
@Component
class EUTaxCalculator implements TaxCalculator {

    @Override
    public boolean canHandle(String originCountry, LocalDate parcelDate) {
        return List.of("ES", "FR", "RO").contains(originCountry);
    }

    public double calculate(double tobaccoValue, double regularValueUseless) { // a bit of a loss: democracy = the tyranny of majority
        return tobaccoValue / 3;
    }

}