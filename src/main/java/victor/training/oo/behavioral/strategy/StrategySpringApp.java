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
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		CustomsTax tax = selectTaxCalculator(originCountry);
		return tax.compute(tobaccoValue, regularValue);
	}

	private CustomsTax selectTaxCalculator(String originCountry) {
//		return switch (originCountry) {
//			case "UK" -> new UKCustomsTax();
//			case "CN" -> new CNCustomsTax();
//			case "FR","ES","RO" -> new EUCustomsTax();
//			default -> throw new IllegalArgumentException("Hai siktir: " + originCountry);
//		};

		switch (originCountry) {
		case "UK": return new UKCustomsTax();
		case "CN": return new CNCustomsTax();
		case "FR":
		case "ES": // other EU country codes...
		case "RO": return new EUCustomsTax();
		default: throw new IllegalArgumentException("Hai siktir: " + originCountry);
		}
	}
}
interface CustomsTax {
	double compute(double tobaccoValue, double regularValue);
}
class UKCustomsTax implements CustomsTax {
	@Override
	public double compute(double tobaccoValue, double regularValue) {
		// un pic mai mult cod
		// un pic mai mult cod
		// un pic mai mult cod
		// un pic mai mult cod
		// un pic mai mult cod
		// un pic mai mult cod
		return tobaccoValue/2 + regularValue/2;
	}
}

class CNCustomsTax implements CustomsTax {
	public double compute(double tobaccoValue, double regularValue) {
		// uite frate pun si io aicea inca putin cod
		// uite frate pun si io aicea inca putin cod
		// uite frate pun si io aicea inca putin cod
		// uite frate pun si io aicea inca putin cod
		// uite frate pun si io aicea inca putin cod
		return tobaccoValue + regularValue;
	}
}
class EUCustomsTax implements CustomsTax {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}