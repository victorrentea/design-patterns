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
	
	@Autowired
	CustomsService service;
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectTaxCalculator(originCountry); 
		return calculator.calculate(tobaccoValue, regularValue);
	}
	@Autowired
	List<TaxCalculator> toate; 

	private TaxCalculator selectTaxCalculator(String originCountry) {
		for (TaxCalculator calculator : toate) {
			if (calculator.accepts(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("JDD Not a valid country ISO2 code: " + originCountry);
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
	boolean accepts(String isoCode);
}
@Service
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue/3;
	}
	public boolean accepts(String isoCode) {
		return asList("RO","ES","FR").contains(isoCode); // TODO
	}
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	public final static String ISO_CODE="CN";
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue + regularValue*2;
	}
	public boolean accepts(String isoCode) {
		return "CN".equals(isoCode); 
	}
}
@Service
class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// mult cod aici
		return tobaccoValue/2 + regularValue;
	}

	public boolean accepts(String isoCode) {
		return "UK".equals(isoCode); 
	}
}

