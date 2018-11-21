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
	public void run(String... args) throws Exception {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CH,100,100) = " + service.computeCustomsTax("CH", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
class UKCustomsTaxCalculator implements CustomsTaxCalculator {
	public double calculateTax(double tobacoValue, double regularValue) {
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		// 30 more lines of logic here
		return tobacoValue/2 + regularValue/2; 
	}

	public boolean accept(String originCountry) {
		return "UK".equals(originCountry); 
	}
}
class CHCustomsTaxCalculator implements CustomsTaxCalculator {
	public double calculateTax(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}

	public boolean accept(String originCountry) {
		return "CH".equals(originCountry); 
	}
}
class EUCustomsTaxCalculator implements CustomsTaxCalculator{
	public boolean accept(String originCountry) {
		return asList("FR", "ES", "RO").contains(originCountry);
	}
	public double calculateTax(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
}



class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		CustomsTaxCalculator calculator = determineCalculator(originCountry); 
		return calculator.calculateTax(tobacoValue, regularValue);
	}

	private CustomsTaxCalculator determineCalculator(String originCountry) {
		List<CustomsTaxCalculator> calculators = asList(new UKCustomsTaxCalculator(), new CHCustomsTaxCalculator(),  new EUCustomsTaxCalculator() );
		return calculators.stream()
				.filter(c -> c.accept(originCountry))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}
}
