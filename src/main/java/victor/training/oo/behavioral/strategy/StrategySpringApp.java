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
		TaxCalculator calculator = chooseTaxCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}

	private TaxCalculator chooseTaxCalculator(String originCountry) {
		switch (originCountry) {
		case "UK": return new UKCustomsTaxCalculator();
		case "CN": return new ChinaCustomsTaxCalculator();
		case "FR":
		case "ES": // other EU country codes...
		case "RO": return new EUCustomsTaxCalculator();
		default:
			throw new IllegalStateException("JDD Unexpected value: " + originCountry);
		}
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
}

class EUCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}
class ChinaCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
class UKCustomsTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/2 + regularValue;
	}
}