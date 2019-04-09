package victor.training.oo.behavioral.strategy;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	
	@Autowired
	private ConfigProvider configProvider; 
	
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	@Autowired
	private CustomsService service;
	
	public void run(String... args) throws Exception {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CH,100,100) = " + service.computeCustomsTax("CH", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
class CustomsService {
	public double computeCustomsTax(String originCountry, double tobacoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer taxComputer = getTaxComputerBy(originCountry); 
		return taxComputer.compute(tobacoValue, regularValue);
	}

	@Autowired
	private List<TaxComputer> computers;
//	@Autowired
//	private Optional<EUTaxComputer> amOare;
	
	private TaxComputer getTaxComputerBy(String originCountry) { 
		return computers.stream()
					.filter(c -> c.accept(originCountry))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}
}

interface TaxComputer {
	double compute(double tobacoValue, double regularValue);
	boolean accept(String originCountry);
}
@Service
class EUTaxComputer  implements TaxComputer {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
	public boolean accept(String originCountry) {
		return asList("RO","ES","FR").contains(originCountry);
	}
}
@Service
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean accept(String originCountry) {
		return "CH".equals(originCountry); 
	}
}
@Service
class UKTaxComputer  implements TaxComputer {
	public double compute(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
	public boolean accept(String originCountry) {
		return "UK".equals(originCountry); 
	}
}

