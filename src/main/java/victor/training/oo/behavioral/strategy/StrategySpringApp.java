package victor.training.oo.behavioral.strategy;

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
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator;
		taxCalculator = selectTaxCalculator(originCountry);
		return taxCalculator.calculate(tobaccoValue, regularValue);
	}

	@Autowired
	public List<TaxCalculator> taxCalculators;

	private TaxCalculator selectTaxCalculator(String originCountry) {
		for (TaxCalculator calculator : taxCalculators) {
			if (calculator.isApplicable(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalStateException("Unexpected value: " + originCountry);
	}
}
interface TaxCalculator {
	boolean isApplicable(String originCountry);
	double calculate(double tobaccoValue, double regularValue);
}
@Service
class UKTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/2 + regularValue;
	}
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return "CN".equals(originCountry);
	}
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String originCountry) {
		return Arrays.asList("RO","ES","FR","IT").contains(originCountry);
	}
	public double calculate(double tobaccoValue, double regularValue) {
		return tobaccoValue/3;
	}
}


