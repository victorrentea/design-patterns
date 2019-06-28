package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
	private CustomsService service;

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}


//		return switch (originCountry) {
//			case "UK" -> new UKCustomsTax();
//			case "CN" -> new CNCustomsTax();
//			case "FR","ES","RO" -> new EUCustomsTax();
//			default -> throw new IllegalArgumentException("Hai siktir: " + originCountry);
//		};

@Service
class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		CustomsTax tax = selectTaxCalculator(originCountry);
		return tax.compute(tobaccoValue, regularValue);
	}

	@Autowired
	private List<CustomsTax> taxes;
	private CustomsTax selectTaxCalculator(String originCountry) {
        for (CustomsTax tax : taxes) {
            if (tax.accepts(originCountry)) {
                return tax;
            }
        }
		throw new IllegalArgumentException("Hai siktir: " + originCountry);
	}
}
interface CustomsTax {
	double compute(double tobaccoValue, double regularValue);
	boolean accepts(String countryCode);
}

@Component
class UKCustomsTax implements CustomsTax {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/2 + regularValue/2;
	}
	public boolean accepts(String countryCode) {
		return "UK".equals(countryCode);
	}
}

@Component
class CNCustomsTax implements CustomsTax {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
	public boolean accepts(String countryCode) {
		return "CN".equals(countryCode);
	}
}
@Component
class EUCustomsTax implements CustomsTax {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
	public boolean accepts(String countryCode) {
		return asList("RO","ES","FR").contains(countryCode);
	}
}