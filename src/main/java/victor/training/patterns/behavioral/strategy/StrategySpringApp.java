package victor.training.patterns.behavioral.strategy;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	
	private ConfigProvider configProvider = new ConfigFileProvider();
	@Autowired
		CustomsService service;

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("NL", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	@Autowired
	List<TaxCalculator> all;

	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectTaxCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}

	private TaxCalculator selectTaxCalculator(String originCountry) {

		for (TaxCalculator calculator : all) {
			if (calculator.accepts(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalStateException("Unexpected value: " + originCountry);
	}
}
interface TaxCalculator {
	boolean accepts(String originCountry);
	double calculate(double tobaccoValue, double regularValue);
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "CN".equals(originCountry); // NPE-driven development
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class BrexitTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		// John came just a bit of logic
		// Maria she 3
		// Maria she 3
		// Maria she 3
		return tobaccoValue/2 + regularValue;
	}
}

@Service
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return asList("NL","FR","ES").contains(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValueUNUSED_AUCH) {
		return tobaccoValue/3;
	}
}
