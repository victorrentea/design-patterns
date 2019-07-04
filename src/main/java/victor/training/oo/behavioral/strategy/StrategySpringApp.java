package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.Async;
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
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator pece = chooseTaxCalculator(originCountry);
		return pece.calculate(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxCalculator> list;

	private TaxCalculator chooseTaxCalculator(String originCountry) {
		for (TaxCalculator taxCalculator : list) {
			if (taxCalculator.accepts(originCountry)) {
				return taxCalculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}
}

interface TaxCalculator {
	boolean accepts(String originCountry);
	double calculate(double tobaccoValue, double regularValue);
}

@Service
class UKTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		// 2016-02-10 Gigel: pun si io aicea iful ala de-mi trebuie mie
		// 2016-06-10 Marcel: pun si io aicea iful ala de-mi trebuie mie
		return tobaccoValue/2 + regularValue/2;
	}
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "CN".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

@Service
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return Arrays.asList("RO","ES", "FR").contains(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}