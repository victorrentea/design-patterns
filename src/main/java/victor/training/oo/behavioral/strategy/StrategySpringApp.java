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
	
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
	@Autowired
	CustomsService service;
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	private Map<String, TaxComputer> computerMap = new HashMap<>();

	public CustomsService(List<TaxComputer> allComputers) {
		if (allComputers.isEmpty()) {
			throw new IllegalArgumentException();
		}
		for (TaxComputer computer : allComputers) {
			for (String country : computer.getApplicableCountries()) {
				computerMap.put(country, computer);
			}
		}
	}

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer taxComputer = findTaxComputerByOriginCountry(originCountry);
		return taxComputer.compute(tobaccoValue, regularValue);
	}

	private TaxComputer findTaxComputerByOriginCountry(String originCountry) {
		if (!computerMap.containsKey(originCountry)) {
			throw new IllegalArgumentException("Unkown country:  " + originCountry);
		}
		return computerMap.get(originCountry);
	}
}
interface TaxComputer {
	double compute(double tobaccoValue, double regularValue);
	List<String> getApplicableCountries();
}

@Service
class ChinaTaxComputer implements TaxComputer {
		public double compute(double tobaccoValue, double regularValue) {
		// + 20 linii
		// + 20 linii
		// + 20 linii
		return tobaccoValue + regularValue;
	}

	@Override
	public List<String> getApplicableCountries() {
		return Arrays.asList("CN");
	}
}
@Service
class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// gigel las si eu aici 10 linii de cod
		// maria: si eu + 15
		// Oleg: + 25
		return tobaccoValue/2 + regularValue;
	}

	@Override
	public List<String> getApplicableCountries() {
		return Arrays.asList("UK");
	}
}
@Service
class EUTaxComputer implements TaxComputer {
//	public double compute(double... array) {       compute(1);   compute(1,2), compute(1,2,3,4,5,6,7,9)
//	public double compute(double[] dinCareAmNevoieDe2_ghiciCare) {
//	public double compute(OClasaMareCu10Campuri dinCareAmNevoieDe2_ghiciCare) {
	public double compute(double tobaccoValue, double regularValue) {
		// multa logica
		return tobaccoValue/3;
	}

	@Override
	public List<String> getApplicableCountries() {
		return Arrays.asList("FR","ES","RO");
	}
}
