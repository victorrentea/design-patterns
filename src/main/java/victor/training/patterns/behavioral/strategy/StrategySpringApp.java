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

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	@Autowired
	CustomsService service;
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
		TaxComputer computer = selectTaxCalculator(originCountry);
		return computer.compute(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxComputer> taxComputers;
	private TaxComputer selectTaxCalculator(String originCountry) {
		return taxComputers.stream()
			.filter(c -> c.appliesTo(originCountry))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));

//		switch (originCountry) {
//			case "UK":
//				return new UKTaxComputer();
//			case "CN":
//				return new ChinaTaxComputer();
//			case "FR":
//			case "ES":
//			case "RO":
//				return new EUTaxComputer();
//			default:
//				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}
}

@Service
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean appliesTo(String isoCode) {
		return "CN".equals(isoCode);
	}
}

@Service
class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// big
		// big
		// big
		// big
		// big
		// big
		return tobaccoValue / 2 + regularValue;
	}

	@Override
	public boolean appliesTo(String isoCode) {
		return "UK".equals(isoCode);
	}

	i

}

@Service
class EUTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 3;
	}

	@Override
	public boolean appliesTo(String isoCode) {
		return Arrays.asList("FR", "ES", "RO", "BE", "NL").contains(isoCode);
	}

}
	nterface TaxComputer {
	double compute(double tobaccoValue,double regularValue);
//	List<String> getCounty(); leaking too much implem details
	boolean appliesTo(String isoCode);
	}

