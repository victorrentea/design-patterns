package victor.training.patterns.behavioral.strategy;

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

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer computer = selectTaxCalculator(originCountry);
		return computer.compute(tobaccoValue, regularValue);
	}

	private TaxComputer selectTaxCalculator(String originCountry) {
		// other EU country codes...
		switch (originCountry) {
			case "UK":
				return new UKTaxComputer();
			case "CN":
				return new ChinaTaxComputer();
			case "FR":
			case "ES":
			case "RO":
				return new EUTaxComputer();
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
//		return switch (originCountry) {
//			case "UK" -> new UKTaxComputer();
//			case "CN" -> new ChinaTaxComputer();
//// other EU country codes...
//			case "FR", "ES", "RO" -> new EUTaxComputer();
//			default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		};
	}
}

class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// big
		// big
		// big
		// big
		// big
		// big
		return tobaccoValue / 2 + regularValue;
	}

	i

}

class EUTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 3;
	}

}
	nterface TaxComputer {
	double compute(double tobaccoValue,double regularValue);
	}

