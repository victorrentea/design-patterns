package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.strategy.CustomsService.SupportedCountry;

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

	private TaxCalculator selectTaxCalculate(SupportedCountry originCountry) {

//		return context.getBean(originCountry.calculatorClass);
//
		return switch (originCountry) {
			case UK -> new UKTaxCalculator();
			case CN -> new ChinaTaxCalculator(); // other EU country codes...
			case FR, ES, RO -> new EUTaxCalculator();
//			default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		};
	}

	enum SupportedCountry {
		UK,
		CN,

		FR, ES, RO, RS;//, RS(calculatorClass);
	}
}

@Component
class UKTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		//  LOGIC
		//  LOGIC JOhn +3
		return tobaccoValue / 2 + regularValue;
	}
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValue) {
		// LOGIC
		// LOGIC
		return tobaccoValue + regularValue;
	}
}

@Component
class EUTaxCalculator implements TaxCalculator {
	public double calculate(double tobaccoValue, double regularValueUNUSED_YUCK) {
		// LOGIC
		// LOGIC
		return tobaccoValue / 3;
	}
}

interface TaxCalculator {
	double calculate(double tobaccoValue, double regularValue);
}
