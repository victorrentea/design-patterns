package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;

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
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("MX", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	private static final Map<?, TaxCalculator> MAP = Map.of(
		"UK", new UKTaxCalculator(),
		"CN", new ChinaTaxCalculator(),
		"BE", new EUTaxCalculator()
		//47 countries ==> externalize in a property file. >> here MAP WINS!
	);

	static {
//		MAP = readFromProperties
	}

	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
//		return MAP.get(originCountry).calculateTax(tobaccoValue, regularValue);
//		MAP.compute(originCountry, (k,b) -> ); // TODO

		TaxCalculator calculator = selectCalculator(originCountry);
		return calculator.calculateTax(tobaccoValue, regularValue);
	}

	private TaxCalculator selectCalculator(String originCountry) {
		switch (originCountry) {
			case "UK":
				return new UKTaxCalculator();
			case "CN":
				return new ChinaTaxCalculator();
			case "FR":
			case "BE":
			case "NL":
			case "ES": // other EU country codes...
			case "RO":
				return new EUTaxCalculator();
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
	}
}

class ChinaTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

class UKTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValue) {
		// heavy logic
		return tobaccoValue / 2 + regularValue;
	}
}

class EUTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValueUNUSED_WHY_IT_THIS_HERE_YOU_WONDER_IN_2_YEARS) { // loss of precision.
		return tobaccoValue / 3;
	}
}

interface TaxCalculator {
	double calculateTax(double tobaccoValue, double regularValue);
}
