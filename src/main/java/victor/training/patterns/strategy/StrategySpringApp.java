package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	private ConfigProvider configProvider = new ConfigFileProvider();

	@Autowired
	private CustomsService service;
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
	@Autowired
	private ChinaTaxCalculator chinaTaxCalculator;
	@Autowired
	private BrexitTaxCalculator brexitTaxCalculator;
	@Autowired
	private EUTaxCalculator euTaxCalculator;

	public double calculateCustomsTax(String originCountry,
									  double tobaccoValue,
									  double regularValue) { // UGLY API we CANNOT change
		ITaxCalculator taxCalculator = null;
		switch (originCountry) {
			case "UK":
				taxCalculator =  brexitTaxCalculator;break;
			case "CN":
				taxCalculator =  chinaTaxCalculator;break;
			case "FR":
			case "ES": // other EU country codes...
			case "RO":
				taxCalculator =  euTaxCalculator;break;
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
		return taxCalculator.compute(tobaccoValue,regularValue);
	}
}

interface ITaxCalculator {
	public double compute(double tobaccoValue, double regularValue);
}
@Service
class ChinaTaxCalculator implements ITaxCalculator{
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class BrexitTaxCalculator implements ITaxCalculator{
	public double compute(double tobaccoValue, double regularValue) {
		// 2002 // 3
		// 2002 // 3
		// 2003  // a
		// 2003  // a
		// 2003  // a
		// 2003  // a
		// 2003  // a
		return tobaccoValue / 2 + regularValue;
	}

}
@Service
class EUTaxCalculator implements ITaxCalculator{

	public double compute(double tobaccoValue, double degeabaValue) {
		return tobaccoValue / 3;
	}
}