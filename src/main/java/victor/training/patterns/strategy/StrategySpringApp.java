package victor.training.patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
	@Autowired
	CustomsService service;
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
		Country e = Country.valueOf(originCountry);
		TaxCalculator calculator = selectCalculator(originCountry);
		return calculator.compute(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxCalculator> all;
	private  TaxCalculator selectCalculator(String originCountry) {
		return all.stream().filter(c -> c.isApplicable(originCountry)).findFirst().orElseThrow();
	}
}

// the condition

interface TaxCalculator { // enforce the same contract !!!v provide structure
	boolean isApplicable(String countyIso);
	double compute(double tobaccoValue, double regularValue);
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String countyIso) {
		return "CN".equals(countyIso);
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class BrexitTaxCalculator implements TaxCalculator{
	public boolean isApplicable(String countyIso) {
		return "UK".equals(countyIso);
	}
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}
}
@Service
class EUTaxCalculator implements TaxCalculator{
	@Override
	public boolean isApplicable(String countyIso) {
		return List.of("ES", "FR", "RO", "NL").contains(countyIso);
	}

	public double compute(double tobaccoValue, double unused) {
		return tobaccoValue / 3;
	}
}
