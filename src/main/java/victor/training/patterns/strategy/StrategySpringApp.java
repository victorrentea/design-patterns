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
		ITaxCalculator taxCalculator = selectCalculator(CountryCode.valueOf(originCountry));
		return taxCalculator.compute(tobaccoValue,regularValue);
	}

	// switchu are 3 reguli:
	// 1) sta singur in metoda
	// 2) are default thnrtow (JDD)
	// 3) in case 1-2-3 lini -> extract methoda
	private ITaxCalculator selectCalculator(CountryCode originCountry) {
		return switch (originCountry) {
			case UK -> brexitTaxCalculator;
			case CN -> chinaTaxCalculator; // other EU country codes...
			case FR, ES, RO -> euTaxCalculator;
		};
	}
}
enum CountryCode {
	RO,
	FR,
//	DONBAS,
	ES,
	CN,
	UK,
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