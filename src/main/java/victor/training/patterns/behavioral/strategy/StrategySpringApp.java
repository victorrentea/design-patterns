package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

interface TaxComputer {
	double compute(double tobaccoValue, double regularValue);

	//	List<String> acceptableCountries();
	boolean isApplicable(String originCountry);
}

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
		TaxComputer taxComputer = selectTaxComputer(originCountry);
		return taxComputer.compute(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxComputer> toate;

	private TaxComputer selectTaxComputer(String originCountry) {

		for (TaxComputer computer : toate) {
			if (computer.isApplicable(originCountry)) {
				return computer;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}

	// DRY < SRP
}

@Service
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean isApplicable(String originCountry) {
		return "CN".equals(originCountry);
	}
}

@Service
class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}

	@Override
	public boolean isApplicable(String originCountry) {
		return "UK".equals(originCountry) /*|| originCountry.i*/;
	}
}

@Service
class EUTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double degeabaRegularValue) {
		return tobaccoValue / 3;
	}

	@Override
	public boolean isApplicable(String originCountry) {
		return asList("RO", "ES", "FR").contains(originCountry);
	}
}