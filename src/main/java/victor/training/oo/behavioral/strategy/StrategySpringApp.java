package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
		TaxCalculator calculator = selectTaxCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}

	private final static Map<String, TaxCalculator> mistake = new HashMap<>();
	static {
	    mistake.put("UK", new UKTaxCalculator());
    }
	private TaxCalculator selectTaxCalculator(String originCountry) {

	    return Optional.ofNullable(mistake.get(originCountry))
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + originCountry));
//
//		switch (originCountry) {
//		case "UK": return new UKTaxCalculator();
//		case "CN": return new ChinaTaxCalculator();
//		case "FR":
//		case "ES": // other EU country codes...
//		case "RO": return new EUTaxCalculator();
//		default: throw new IllegalStateException("Unexpected value: " + originCountry);
//		}
	}

}

class EUTaxCalculator implements TaxCalculator{
	public double calculate(double tobaccoValue, /*useless*/ double regularValue) {
		return tobaccoValue/3;
	}
}

class ChinaTaxCalculator implements TaxCalculator{
	public double calculate(double tobaccoValue, double regularValue) {
		// Joe adds here 1 more line of logic
		// Joe adds here 1 more line of logic
		return tobaccoValue + regularValue;
	}

}

class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// Joe adds here 1 more line of logic
		// So does mary
		return tobaccoValue/2 + regularValue;
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
}