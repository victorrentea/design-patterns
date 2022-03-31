package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import victor.training.patterns.behavioral.strategy.TaxCalculator.ChinaTaxCalculator;
import victor.training.patterns.behavioral.strategy.TaxCalculator.EUTaxCalculator;
import victor.training.patterns.behavioral.strategy.TaxCalculator.UKTaxCalculator;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	public void run(String... args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
	}
}

class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator = selectCalculatorForCountry(originCountry);
		return taxCalculator.calculate(tobaccoValue, regularValue);
	}

	private TaxCalculator selectCalculatorForCountry(String originCountry) {
		// other EU country codes...
		switch (originCountry) {
			case "UK":
				return new UKTaxCalculator();
			case "CN":
				return new ChinaTaxCalculator();
			case "FR":
			case "ES":
			case "RO":
				return new EUTaxCalculator();
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
//		return switch (originCountry) {
//			case "UK" -> new UKTaxCalculator();
//			case "CN" -> new ChinaTaxCalculator(); // other EU country codes...
//			case "FR", "ES", "RO" -> new EUTaxCalculator();
//			default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		};
	}

}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
}
class ChinaTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// logica
		// logica
		// logica
		// logica
		return tobaccoValue + regularValue;
	}
}
class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// un pic mai complicate
		// un pic mai complicate
		return tobaccoValue / 2 + regularValue;
	}
}
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) { // pret platit de minoritatile dintr-o tara
		return tobaccoValue / 3;
	}
}
