package victor.training.oo.behavioral.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class).profiles("localProps").run(args);
	}

	@Autowired
	private CustomsService service;
	
//	private ConfigProvider configProvider = new ConfigFileProvider();
	@Autowired
	private ConfigProvider configProvider;
	
	public void run(String... args) throws Exception {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CH,100,100) = " + service.computeCustomsTax("CH", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
//		switch (originCountry) { // INITIAL(
//		case "UK": return tobacoValue/2 + otherValue/2; 
//		case "CH": return tobacoValue + otherValue;
//		case "FR": 
//		case "ES": // other EU country codes...
//		case "RO": return tobacoValue/3;
//		default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		} // INITIAL)
		// SOLUTION(
		TaxCalculator taxCalculator = getTaxCalculatorFor(originCountry); 
		return taxCalculator.computeTax(tobacoValue, regularValue);
		// SOLUTION)
	}
	
	// SOLUTION (
	@Autowired
	private List<TaxCalculator> calculators;
	
	public TaxCalculator getTaxCalculatorFor(String originCountry) {
//		for (TaxCalculator calculator : calculators) {
//			if (calculator.isApplicable(originCountry)) {
//				return calculator;
//			}
//		}
		// throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		// or, Java8-style:
		return calculators.stream()
				.filter(c -> c.isApplicable(originCountry))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}
	// SOLUTION )
}
