package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

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
	@Autowired
	private CustomsService service;

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) throws Exception {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator a = selectTaxCalculator(originCountry);
		return a.compute(tobacoValue, regularValue);
	}

	@Autowired
	private List<TaxCalculator> calculators;

	private TaxCalculator selectTaxCalculator(String originCountry) {

		return calculators.stream()
				.filter(c -> c.canHandle(originCountry))
				.findFirst()
				.orElseThrow(() ->new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));

	}

}
interface TaxCalculator {
	boolean canHandle(String originCountry);
	double compute(double tobacoValue, double regularValue);
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	public boolean canHandle(String originCountry) {
		return "CN".equals(originCountry);
	}

	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
}
@Service
class UKTaxCalculator implements TaxCalculator {
	public boolean canHandle(String originCountry) {
		return "UK".equals(originCountry);
	}
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
}
@Service
class EUTaxCalculator implements TaxCalculator {
	public boolean canHandle(String originCountry) {
		return Arrays.asList("FR","ES","RO","NL").contains(originCountry);
	}

	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
}
