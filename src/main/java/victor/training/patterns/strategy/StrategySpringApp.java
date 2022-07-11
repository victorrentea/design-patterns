package victor.training.patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
	@Autowired
		CustomsService service ;

	@ServiceActivator
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Component
class CustomsService {

	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		return selectCalculator(originCountry).calculate(tobaccoValue, regularValue);
	}

//	private static final Map<String, Class<? extends TaxCalculator>> calculators = Map.of(
//		"UK", UKTaxCalculator.class,
//			"CN", new
//	);
	@Autowired
	List<TaxCalculator> toate;
	private TaxCalculator selectCalculator(String originCountry) {
		for (TaxCalculator calculator : toate) {
			if (calculator.applicableFor(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}
}
interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);

	boolean applicableFor(String originCountry);
}

