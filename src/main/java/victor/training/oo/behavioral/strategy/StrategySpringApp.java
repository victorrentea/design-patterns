package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

import static java.util.Arrays.asList;

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
		TaxCalculator taxCalculator = selectTaxCalculator(originCountry);
		return taxCalculator.compute(tobaccoValue, regularValue);
	}

	private TaxCalculator selectTaxCalculator(String originCountry) {
		List<TaxCalculator> calculators = asList(new UKTaxCalculator(), new ChinaTaxCalculator(), new EUTaxCalculator());

		for (TaxCalculator calculator : calculators) {
			if (calculator.supports(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}

}
interface TaxCalculator {
	double compute(double tobaccoValue, double regularValue);
	boolean supports(String originCountry);
}
class EUTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
	public boolean supports(String originCountry) {
		return asList("RO","ES","FR").contains(originCountry);
	}
}
class ChinaTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
	public boolean supports(String originCountry) {
		return "CN".equals(originCountry);
	}
}

class UKTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/2 + regularValue;
	}
	public boolean supports(String originCountry) {
		return "UK".equals(originCountry);
	}
}