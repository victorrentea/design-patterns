package victor.training.oo.behavioral.strategy;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

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
	
	@Autowired
	CustomsService service;
	public void run(String... args) throws Exception {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}


@Service
class CustomsService {
	@Autowired
	private List<TaxCalculator> calculators;
	
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = determineCalculator(originCountry); 
		return calculator.compute(tobacoValue, regularValue);
	}

	private TaxCalculator determineCalculator(String originCountry) {
		return calculators.stream()
				.filter(c -> c.accepts(originCountry))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}

}
interface TaxCalculator {
	double compute(double tobacoValue, double regularValue);
	boolean accepts(String originCountry);
}

@Service
class EUTaxCalculator implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
	public boolean accepts(String originCountry) {
		return asList("RO","FR","ES").contains(originCountry);
	}
}

@Service
class ChinaTaxCalculator implements TaxCalculator{
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean accepts(String originCountry) {
		return "CN".equalsIgnoreCase(originCountry);
	}
}
@Service
class RussiaTaxCalculator implements TaxCalculator{
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean accepts(String originCountry) {
		return "RU".equalsIgnoreCase(originCountry);
	}
}

@Service
class UKTaxCalculator implements TaxCalculator{
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}

	public boolean accepts(String originCountry) {
		return "UK".equals(originCountry); 
	}
}
