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
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}














class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator computer = selectTaxCalculator(originCountry); 
		return computer.computeTax(tobacoValue, regularValue);
	}

	private TaxCalculator selectTaxCalculator(String originCountry) {
		List<TaxCalculator> calculators = asList(
				new UKTaxCalculator(), 
				new CNTaxCalculator(),
				new EUTaxCalculator());
		return calculators.stream()
			.filter(c -> c.supports(originCountry))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}

}

interface TaxCalculator {
	double computeTax(double tobacoValue, double regularValue);
	boolean supports(String originCountry);
}
class UKTaxCalculator implements TaxCalculator {
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
	public boolean supports(String originCountry) {
		return "UK".equals(originCountry);
	}
}
class CNTaxCalculator implements TaxCalculator{
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean supports(String originCountry) {
		return "CN".equals(originCountry);
	}
}
class EUTaxCalculator implements TaxCalculator{
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
	public boolean supports(String originCountry) {
		return asList("RO", "FR", "ES").contains(originCountry);
	}
}






