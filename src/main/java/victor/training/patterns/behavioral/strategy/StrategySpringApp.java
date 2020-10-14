package victor.training.patterns.behavioral.strategy;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class StrategySpringApp  {
	public static void main(String[] args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));


		System.out.println("HOHOHOHOHOHOH");

		System.out.println("a".hashCode());
		String s=null;
		switch (s)
		{
		case "a": System.out.println();
		case "b": System.out.println();
		case "c": System.out.println();
			default:
				System.out.println("ERROR");
		}
 
	}  
}

class CustomsService {
	private static final List<TaxComputer> COMPUTERS = asList(new UKTaxComputer(), new ChinaTaxComputer(), new EUTaxComputer());

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer computer = selectTaxComputer(originCountry); 
		return computer.compute(tobaccoValue, regularValue);
	} 
	
	private TaxComputer selectTaxComputer(String originCountry) {
		return COMPUTERS.stream()
				.filter(c->c.canCompute(originCountry)).findAny()
				.orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
	}
}
interface TaxComputer {
	boolean canCompute(String originCountry);
	double compute(double tobaccoValue, double regularValue);	
}
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
	public boolean canCompute(String originCountry) {
		return "CN".equals(originCountry);
	}
}

class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// lots of code 
		return tobaccoValue/2 + regularValue;
	}
	public boolean canCompute(String originCountry) {
		return "UK".contentEquals(originCountry);
	}
}
class EUTaxComputer implements TaxComputer  {
	public double compute(double tobaccoValue, double regularValueUnusedDamnitHustBecauseIWantToIplement__ThePriceToPayForStrategyPattern) {
		return tobaccoValue/3;
	}
	public boolean canCompute(String originCountry) {
		return asList("FR","RO","ES").contains(originCountry);
	}
	
}
