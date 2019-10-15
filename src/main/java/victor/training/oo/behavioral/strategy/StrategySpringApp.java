package victor.training.oo.behavioral.strategy;

import java.util.Hashtable;
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


	public ConfigProvider configProvider = new ConfigFileProvider();

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

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator = null;
		switch (originCountry) {
		case "UK": 
			taxCalculator = new UKTaxComputer();
		case "CH": 
			taxCalculator = new CHTaxComputer();
		case "FR":
		case "ES": // other EU country codes...
		case "RO": taxCalculator = new EUTaxComputer();
//		default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
		return taxCalculator.compute(tobacoValue, regularValue);
	}
}
interface TaxCalculator {

	double compute(double tobacoValue, double regularValue);
	
}
class EUTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
}
class CHTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
}
class UKTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
}





