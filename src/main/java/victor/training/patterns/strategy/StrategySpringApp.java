package victor.training.patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StrategySpringApp.class, args);
    }

    @Autowired
    private CustomsService service;

    // TODO [1] Break CustomsService logic into Strategies
    // TODO [2] Convert it to Chain Of Responsibility
    // TODO [3] Wire with Spring
    public void run(String... args) {
        System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax(
                new Parcel(CountryEnum.valueOf("RO"), 100, 100, LocalDate.now())));
        System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax(
                new Parcel(CountryEnum.valueOf("CN"), 100, 100, LocalDate.now())));
        System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax(
                new Parcel(CountryEnum.valueOf("UK"), 100, 100, LocalDate.now())));
    }
}
