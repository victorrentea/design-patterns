package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.strategy.CustomsService.SupportedCountry;

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
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax(SupportedCountry.RO, 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax(SupportedCountry.CN, 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax(SupportedCountry.UK, 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	@Autowired
	ApplicationContext context;

//	@Value("${}")
//	private Map<String, TaxCalculator> map = new HashMap<>();
//	{
//		map.put("UK", new UKTaxCalculator());
//		map.put("CN", new ChinaTaxCalculator());
//		map.put("RO", new EUTaxCalculator());
//		map.put("", new EUTaxCalculator());
//		map.put("CN", new EUTaxCalculator());
//	}

	public double calculateCustomsTax(SupportedCountry originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectTaxCalculate(originCountry);
		return calculator.calculate(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxCalculator> allCalculators; // OCP

	private TaxCalculator selectTaxCalculate(SupportedCountry originCountry) {
		for (TaxCalculator calculator : allCalculators) {
			if (calculator.canCalculate(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		switch (originCountry) {
//			case UK: return new UKTaxCalculator();
//			case CN: return new ChinaTaxCalculator();
//			case FR:
//			case ES: // other EU country codes...
//			case RO: return new EUTaxCalculator();
//			default:
//				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}

	enum SupportedCountry {
		UK,
		CN,

		FR, ES, RO;//, RS(calculatorClass);
	}
}

@Component
class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		//  LOGIC
		//  LOGIC JOhn +3
		return tobaccoValue / 2 + regularValue;
	}

	@Override
	public boolean canCalculate(SupportedCountry originCountry) {
		return SupportedCountry.UK == originCountry;
	}
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// LOGIC
		// LOGIC
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean canCalculate(SupportedCountry originCountry) {
		return originCountry == SupportedCountry.CN;
	}
}

@Component
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValueUNUSED_YUCK) {
		// LOGIC
		// LOGIC
		return tobaccoValue / 3;
	}

	@Override
	public boolean canCalculate(SupportedCountry originCountry) {
		return Arrays.asList(SupportedCountry.FR, SupportedCountry.RO, SupportedCountry.ES).contains(originCountry);
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);

	boolean canCalculate(SupportedCountry originCountry);
}
