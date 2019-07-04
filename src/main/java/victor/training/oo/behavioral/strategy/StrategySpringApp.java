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
		TaxCalculator pece = chooseTaxCalculator(originCountry);
		return pece.calculate(tobaccoValue, regularValue);
	}

	private TaxCalculator chooseTaxCalculator(String originCountry) {
        return switch (originCountry) {
            case "UK" -> new UKTaxCalculator();
            case "CN" -> new ChinaTaxCalculator();
// other EU country codes...
            case "FR", "ES", "RO" -> new EUTaxCalculator();
            default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
        };
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
}

class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// 2016-02-10 Gigel: pun si io aicea iful ala de-mi trebuie mie
		// 2016-06-10 Marcel: pun si io aicea iful ala de-mi trebuie mie
		// si inca putin cod
		// si inca putin cod
		return tobaccoValue/2 + regularValue/2;
	}
}
class ChinaTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// doar un pic cod
		// doar un pic cod
		// doar un pic cod
		return tobaccoValue + regularValue;
	}
}

class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}