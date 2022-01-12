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
		// other EU country codes...
		switch (originCountry) {
			case "UK":
				return calculateUkTax(tobaccoValue, regularValue);
			case "CN":
				return calculateChinaTax(tobaccoValue, regularValue);
			case "FR":
			case "ES":
			case "RO":
				return calculateEUTax(tobaccoValue);
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}

	private double calculateEUTax(double tobaccoValue) {
		return tobaccoValue / 3;
	}

	private double calculateChinaTax(double tobaccoValue, double regularValue) {
		// complexitate mare
		// complexitate mare
		// complexitate mare
		// complexitate mare
		// complexitate mare
		// complexitate mare
		return tobaccoValue + regularValue;
	}

	private double calculateUkTax(double tobaccoValue, double regularValue) {
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		//20 linii de hard core logic
		return tobaccoValue / 2 + regularValue;
	}
}
