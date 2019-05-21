package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
		ITax tax = getTax(originCountry);
		return tax.compute(tobacoValue, regularValue);

	}

	private ITax getTax(String originCountry) {
		List<ITax> taxe = Arrays.asList(new UKTax(), new CNTax(), new EUTax());
		for (ITax tax : taxe) {
			if (tax.getIsoCodes().contains(originCountry)) {
				return tax;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}
}
interface ITax {
	List<String> getIsoCodes();
	double compute(double tobacoValue, double regularValue);
}
class UKTax implements ITax {
	public List<String> getIsoCodes() {
		return Arrays.asList("UK");
	}
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue /2 + regularValue/2;
	}
}
class CNTax implements ITax {
	public List<String> getIsoCodes() {
		return Arrays.asList("CN");
	}

	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
}
class EUTax implements ITax {
	public List<String> getIsoCodes() {
		return Arrays.asList("RO", "ES", "FR");
	}

	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue /3;
	}
}