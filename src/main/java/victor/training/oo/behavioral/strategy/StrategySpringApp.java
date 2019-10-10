package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		TaxCalculator calculator = selectCalculator(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}
	public static Map<String, TaxCalculator> calculators = new HashMap<>();

	@Autowired
	private List<TaxCalculator> toate;

	private TaxCalculator selectCalculator(String originCountry) {
		for (TaxCalculator taxCalculator : toate) {
			if (taxCalculator.accept(originCountry)) {
				return taxCalculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
	}

	// oter typesafe spring - aware
	enum Type1 {
		VAL1(CNTaxCalculator.class),
		;

		private Class<? extends TaxCalculator> cnTaxCalculatorClass;

		Type1(Class<CNTaxCalculator> cnTaxCalculatorClass) {

			this.cnTaxCalculatorClass = cnTaxCalculatorClass;
		}
	}

}
interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
	boolean accept(String originCountry);
}
@Service
class CNTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue*2;
	}
	public boolean accept(String originCountry) {
			return "CN".equals(originCountry);
		}
}
@Service
class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}

	@Override
	public boolean accept(String originCountry) {
		return "UK".equals(originCountry);
	}
}
@Service
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
	public boolean accept(String originCountry) {
		return Arrays.asList("RO","ES","FR").contains(originCountry);
	}
}





