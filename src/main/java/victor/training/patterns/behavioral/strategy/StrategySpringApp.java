package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

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
		return taxComputer.computeTax(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxComputer> toti;

	private TaxComputer selectTaxComputer(String originCountry) {
		for (TaxComputer taxComputer : toti) {
			if (taxComputer.hasCountry(originCountry)) {
				return taxComputer;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}
}

@Service
class EUTaxComputer implements TaxComputer {
	@Override
	public boolean hasCountry(String isoCode) {
		return Arrays.asList("RO", "FR", "ES").contains(isoCode);
	}

	public double computeTax(double tobaccoValue, double regularValueDegeaba) {
		return tobaccoValue / 3;
	}
}

@Service
class ChinaTaxComputer implements TaxComputer {
	@Override
	public boolean hasCountry(String isoCode) {
		return "CN".equals(isoCode);
	}

	// "CN"
	public double computeTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

@Service
class UKTaxComputer implements TaxComputer {
	@Override
	public boolean hasCountry(String isoCode) {
		return "UK".equals(isoCode);
	}

	public double computeTax(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}
}

interface TaxComputer {
	boolean hasCountry(String isoCode);

	double computeTax(double tobaccoValue, double regularValue);
}


