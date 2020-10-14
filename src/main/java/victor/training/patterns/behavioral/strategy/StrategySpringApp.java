package victor.training.patterns.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class StrategySpringApp  {
	public static void main(String[] args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
	}
}

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer computer = selectTaxComputer(originCountry); 
		return computer.compute(tobaccoValue, regularValue);
	} 
	
	private static final Map<String, TaxComputer> COMPUTERS = new HashMap<>();
	
	// Map is the BEST if you load the mapping from a properties file (in case they are many)
	//tax.country.UK=victor.training.patterns.behavioral.strategy.UKTaxComputer.UKTaxComputer()
	static {
		// load the map from file here
		COMPUTERS.put("UK", new UKTaxComputer());
		COMPUTERS.put("CN", new ChinaTaxComputer());
		COMPUTERS.put("ES", new EUTaxComputer());
		COMPUTERS.put("RO", new EUTaxComputer());
		COMPUTERS.put("FR", new EUTaxComputer());
	}

	private TaxComputer selectTaxComputer(String originCountry) {
		return Objects.requireNonNull(COMPUTERS.get(originCountry));
		
		 
		
//		switch (originCountry) { 
//		case "UK": return new UKTaxComputer(); 
//		case "CN": return new ChinaTaxComputer();
//		case "FR": 
//		case "ES": // other EU country codes...
//		case "RO": return new EUTaxComputer();
//		default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}
}
interface TaxComputer {
	double compute(double tobaccoValue, double regularValue);	
}
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// many lines  30
		return tobaccoValue + regularValue;
	}
}

class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// lots of code 
		return tobaccoValue/2 + regularValue;
	}
}
class EUTaxComputer implements TaxComputer  {
	public double compute(double tobaccoValue, double regularValueUnusedDamnitHustBecauseIWantToIplement__ThePriceToPayForStrategyPattern) {
		return tobaccoValue/3;
	}
	
}
