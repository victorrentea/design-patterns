package victor.training.oo.behavioral.strategy;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}


	public ConfigProvider configProvider = new ConfigFileProvider();

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	public void run(String... args) throws Exception {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CH,100,100) = " + service.computeCustomsTax("CH", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator = selectCalculator(originCountry);
		return taxCalculator.compute(tobacoValue, regularValue);
	}
	//@Autowired @Inject
	//List<TaxCalcuto>
	
	private TaxCalculator selectCalculator(String originCountry) {
		List<TaxCalculator> calculators = asList(new UKTaxComputer(), new EUTaxComputer(), new CHTaxComputer());
		
		return calculators.stream()
				.filter(c -> c.isApplicable(originCountry))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
		

	}
}
interface TaxCalculator {
	double compute(double tobacoValue, double regularValue);
	boolean isApplicable(String originCountry);
	
}
class UKTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
	public boolean isApplicable(String originCountry) {
		// TODO Auto-generated method stub
		return "UK".equals(originCountry);
	}
}
class EUTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	
	}
	@Override
	public boolean isApplicable(String originCountry) {
		return asList("RO","ES","FR").contains(originCountry);
	}
}
//@Stateless
class CHTaxComputer implements TaxCalculator {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean isApplicable(String originCountry) {
		return "CH".equals(originCountry);
	}
}






