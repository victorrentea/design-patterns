package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
	
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	@Autowired
	CustomsService service;
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	@Autowired
	List<TaxComputer> toate;

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer taxComputer = selectTaxComputer(originCountry);
		return taxComputer.compute(tobaccoValue, regularValue);
	}
	private TaxComputer selectTaxComputer(String originCountry) {

		for (TaxComputer taxComputer : toate) {
			if (taxComputer.shouldCompute(originCountry)) {
				return taxComputer;
			}
		}
		throw new IllegalArgumentException(originCountry);
	}

}

interface TaxComputer {
	boolean shouldCompute(String originCountry);
	double compute(double tobaccoValue, double regularValue);
}


@Service
class EUTaxComputer implements TaxComputer {
	@Override
	public boolean shouldCompute(String originCountry) {
		return asList("ES","FR","RO").contains(originCountry);
	}
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}
@Service
class ChinaTaxComputer implements TaxComputer {
	@Override
	public boolean shouldCompute(String originCountry) {
		return "CN".equals(originCountry);
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class UKTaxComputer implements TaxComputer {
	@Override
	public boolean shouldCompute(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double compute(double tobaccoValue, double regularValue) {
		// mai e inca un pic de cod
		// pun si eu asta aici
		// si eu
		return tobaccoValue/2 + regularValue;
	}
}
