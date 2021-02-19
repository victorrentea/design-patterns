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
		switch (originCountry) {
			case "UK":
				return new UKTaxComputer().compute(tobaccoValue, regularValue);
			case "CN":
				return new ChinaTaxComputer().compute(tobaccoValue, regularValue);
			case "FR":
			case "ES": // other EU country codes...
			case "RO":
				return new EUTaxComputer().compute(tobaccoValue);
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
	}

	// DRY < SRP


}

class ChinaTaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		//
		//
		//
		return tobaccoValue + regularValue;
	}
}

class UKTaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// COlegu1: las si eu aicea 3 linii cod
		// COlegu1: las si eu aicea 3 linii cod
		// COlegu1: las si eu aicea 3 linii cod
		// COlegu1: las si eu aicea 3 linii cod
		// COlegu1: las si eu aicea 3 linii cod

		// COlega1: las si eu aicea 5 linii cod

		return tobaccoValue / 2 + regularValue;
	}
}

class EUTaxComputer {
	public double compute(double tobaccoValue) {
		return tobaccoValue / 3;
	}

}