package victor.training.patterns.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	
	public ConfigProvider configProvider = new ConfigFileProvider();
	
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
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectTaxCalculator(originCountry);
		return calculator.computeTax(tobaccoValue, regularValue);
	}

	private TaxCalculator selectTaxCalculator(String originCountry) {
		switch (originCountry) {
			case "UK":
				return new UKTaxCalculator();
			case "CN":
				return new ChinaTaxCalculator();
			case "FR":
			case "ES": // other EU country codes...
			case "RO":
				return new EUTaxCalculator();
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
	}
}

interface TaxCalculator {
	double computeTax(double tobaccoValue, double regularValue);
}

class UKTaxCalculator implements TaxCalculator {
	public double computeTax(double tobaccoValue, double regularValue) {
		// MAI E treba serioasa 25-27 linii
		return tobaccoValue / 2 + regularValue;
	}
}

class ChinaTaxCalculator implements TaxCalculator {
	public double computeTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

class EUTaxCalculator implements TaxCalculator {
	public double computeTax(double tobaccoValue, double regularValue__DEGEABA) {
		return tobaccoValue / 3;
	}
}