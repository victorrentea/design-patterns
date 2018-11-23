package victor.training.oo.behavioral.strategy;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	
	@Autowired
	private ConfigProvider configProvider; 
	
	@Autowired
	private CustomsService service;
	// [1] Break CustomsService logic into Strategies
	// [2] Convert it to Chain Of Responsibility
	// [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) throws Exception {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CH,100,100) = " + service.computeCustomsTax("CH", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	@Autowired
	private List<TaxCalculator> calculators;

	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = determineCalculatorForCountry(originCountry); 
		return calculator.calculate(tobacoValue, regularValue);
	}

	private TaxCalculator determineCalculatorForCountry(String originCountry) {
		return calculators.stream()
			.filter(c -> c.canCalculate(originCountry))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException());
	}
}

interface TaxCalculator {
	double calculate(double tobacoValue, double regularValue);
	boolean canCalculate(String countryCode);
}
@Service
class UKTaxCalculator implements TaxCalculator{
	public double calculate(double tobacoValue, double regularValue) {
		// cica, mult cod
		return tobacoValue/2 + regularValue/2;
	}
	public boolean canCalculate(String countryCode) {
		return "UK".equals(countryCode); 
	}
}
@Service
class CHTaxCalculator implements TaxCalculator{
	public double calculate(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}

	public boolean canCalculate(String countryCode) {
		return "CH".equals(countryCode);
	}
}
@Service
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
	public boolean canCalculate(String countryCode) {
		return asList("FR","ES", "RO").contains(countryCode);
	}
	
}
