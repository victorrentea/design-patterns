package victor.training.patterns.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;
import java.util.Optional;

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
	public void run(String... args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator calculator = selectCalculator(originCountry);
		return calculator.compute(tobaccoValue, regularValue);
	}

//	@Value("") //  from .properties = good idea = externalizing the mapping if it keeps changing .
//	private  final Map<String, TaxCalculator> strategies;

	//	= Map.of(
//			"UK", new BrexitTaxCalculator(),
//			"CN", new ChinaTaxCalculator(),
//			"FR", new EUTaxCalculator(),
//			"ES", new EUTaxCalculator(),
//			"RO", new EUTaxCalculator()
//	);

	private static TaxCalculator selectCalculator(String originCountry) {
//		return Optional.ofNullable(strategies.get(originCountry));
//		return switch (originCountry) {
//			case "UK" -> new BrexitTaxCalculator();
//			case "CN" -> new ChinaTaxCalculator();
//			case "FR", "ES", "RO" -> new EUTaxCalculator();
//			default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		};
	}
}
interface TaxCalculator { // enforce the same contract !!!v provide structure
	double compute(double tobaccoValue, double regularValue);
}
class ChinaTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
class BrexitTaxCalculator implements TaxCalculator{
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}
}
class EUTaxCalculator implements TaxCalculator{
	public double compute(double tobaccoValue, double unused) {
		return tobaccoValue / 3;
	}
}
