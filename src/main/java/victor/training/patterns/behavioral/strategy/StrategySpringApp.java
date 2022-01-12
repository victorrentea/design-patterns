package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;
import java.util.List;

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
		TaxCalculator taxCalculator = selectCalculator(originCountry);

		return taxCalculator.calculate(tobaccoValue, regularValue);
	}

	private TaxCalculator selectCalculator(String originCountry) {
		List<TaxCalculator> toti = Arrays.asList(new BrexitTaxCalculator(), new ChinaTaxCalculator(), new EUTaxCalculator());

		for (TaxCalculator taxCalculator : toti) {
			if (taxCalculator.isApplicable(originCountry)) {
				return taxCalculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}
}

class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return "CN".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

class BrexitTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}
}

class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return Arrays.asList("RO", "ES", "FR").contains(originCountry);
	}

	public double calculate(double tobaccoValue, double degeabaRegularValue) {
		return tobaccoValue / 3;
	}
}

interface TaxCalculator {
	boolean isApplicable(String originCountry);

	double calculate(double tobaccoValue, double regularValue);
}
