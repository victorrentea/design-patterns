package victor.training.oo.behavioral.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}


	private ConfigProvider configProvider = new ConfigFileProvider();

	@Autowired
		CustomsService service;
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
@RequiredArgsConstructor
class CustomsService {
	private final List<TaxCalculator> allCalculators;

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = chooseTaxCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}
	private TaxCalculator chooseTaxCalculator(String originCountry) {
        return allCalculators.stream()
                .filter(c -> c.supports(originCountry))
                .findFirst()
                .orElseThrow(() ->new IllegalStateException("JDD Unexpected value: " + originCountry));
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
	boolean supports(String originCountry);
}

@Service
class EUCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
	public boolean supports(String originCountry) {
		return asList("RO", "ES", "FR").contains(originCountry);
	}
}
@Service
class ChinaCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
	public boolean supports(String originCountry) {
        return "CN".equals(originCountry);
    }

}
@Service
class UKCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/2 + regularValue;
	}
	public boolean supports(String originCountry) {
		return "UK".equals(originCountry);
	}
}