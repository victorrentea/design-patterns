package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.*;

import static java.util.Arrays.asList;

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

class CustomsService {

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectTaxCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}

	private final static List<TaxCalculator> calculators = asList(
			new UKTaxCalculator(),
			new ChinaTaxCalculator(),
			new EUTaxCalculator()
	);
	private TaxCalculator selectTaxCalculator(String originCountry) {
		for (TaxCalculator calculator : calculators) {
			if (calculator.accepts(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalStateException("Unexpected value: " + originCountry);
	}

}

class EUTaxCalculator implements TaxCalculator{
	public double calculate(double tobaccoValue, /*useless*/ double regularValue) {
		return tobaccoValue/3;
	}
	public boolean accepts(String countryCode) {
		return asList("RO","ES","FR").contains(countryCode);
	}
}

class ChinaTaxCalculator implements TaxCalculator{
	public double calculate(double tobaccoValue, double regularValue) {
		// Joe adds here 1 more line of logic
		// Joe adds here 1 more line of logic
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean accepts(String countryCode) {
		return "CN".equals(countryCode);
	}

}

class UKTaxCalculator implements TaxCalculator {


	public double calculate(double tobaccoValue, double regularValue) {
		// Joe adds here 1 more line of logic
		// So does mary
		return tobaccoValue/2 + regularValue;
	}

	@Override
	public boolean accepts(String countryCode) {
		return "UK".equals(countryCode);
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
	boolean accepts(String countryCode);
}