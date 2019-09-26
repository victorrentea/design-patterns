package victor.training.oo.behavioral.strategy;

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
		TaxCaculator calculator = selectTaxCalculator(originCountry); 
		return calculator.calculate(tobaccoValue, regularValue);
	}

	private TaxCaculator selectTaxCalculator(String originCountry) {
		switch (originCountry) { 
		case "UK": return new UKTaxCalculator(); 
		case "CN": return new ChinaTaxCalculator(); 
		case "FR": 
		case "ES": // other EU country codes...
		case "RO": return new EUTaxCalculator();
		default: throw new IllegalArgumentException("JDD Not a valid country ISO2 code: " + originCountry);
		}
	}
}

interface TaxCaculator {
	double calculate(double tobaccoValue, double regularValue);
}
class EUTaxCalculator implements TaxCaculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue/3;
	}
}
class ChinaTaxCalculator implements TaxCaculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue + regularValue*2;
	}
}

class UKTaxCalculator implements TaxCaculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue/2 + regularValue;
	}
}

