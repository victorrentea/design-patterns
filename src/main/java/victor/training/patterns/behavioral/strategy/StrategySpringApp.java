package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
	public void run(String... args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class TaxFormulas {
	public static double computeUK(double tobaccoValue, double regularValue) {
		return 1;
	}

	public static double computeCN(double tobaccoValue, double regularValue) {
		return 1;
	}

	public static double computeEU(double tobaccoValue, double regularValue) {
		return 1;
	}
}

//enum NoEUCountry implements TaxableCountry {}
//enum Country {
//	UK(TaxFormulas::computeUK),
//	CN(TaxFormulas::computeCN),
//	FR(TaxFormulas::computeEU),
//	ES(TaxFormulas::computeEU),
//	RO(TaxFormulas::computeEU);
//
//	public final TaxArea taxArea;
//
//	Country(TaxArea taxArea) {
//		this.taxArea = taxArea;
//	}
//}
// Q1: can you find yet another way to associate enums withbehavior?

// Q2: CHANGE REQUEST: I want to apply a LOW-TAX tax calculator (returning 0) for every package
// with tobacco zero and regular < 10
// decouple the selection of the proper tax calculator

class CustomsService {

	public static final List<TaxArea> ALL_AREAS = List.of(new LowTaxArea(), new UKTaxArea(), new ChinaTaxArea(), new EUTaxArea());

	public double calculateCustomsTax(String originCountryCode, double tobaccoValue, double regularValue) {
		TaxArea taxArea = selectTaxArea(originCountryCode, tobaccoValue, regularValue);
		return calculateCustomsTax(taxArea, tobaccoValue, regularValue);
	}

	private TaxArea selectTaxArea(String originCountryCode, double tobaccoValue, double regularValue) {
		return ALL_AREAS.stream()
			.filter(area -> area.shouldApplyTo(originCountryCode, tobaccoValue, regularValue))
			.findFirst()
			.orElseThrow();
	}
	// only use if the enum in not editable

	/// ---------- below: domain

	public double calculateCustomsTax(TaxArea originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		return originCountry.calculateTax(tobaccoValue, regularValue);
	}
}
// Strategy Pattern
interface TaxArea {
	boolean shouldApplyTo(String originCountryCode, double tobaccoValue, double regularValue);
	double calculateTax(double tobaccoValue, double regularValue);
}
class LowTaxArea implements TaxArea {
	public boolean shouldApplyTo(String originCountryCode, double tobaccoValue, double regularValue) {
		return tobaccoValue == 0 && regularValue < 10;
	}
	public double calculateTax(double tobaccoValue, double regularValue) {
		return 0;
	}
}
class UKTaxArea implements TaxArea {
	public boolean shouldApplyTo(String originCountryCode, double tobaccoValue, double regularValue) {
		return "UK".equals(originCountryCode);
	}

	public double calculateTax(double tobaccoValue, double regularValue) {
		// lots of complexity
		return tobaccoValue / 2 + regularValue;
	}
}

class ChinaTaxArea implements TaxArea {
	@Override
	public boolean shouldApplyTo(String originCountryCode, double tobaccoValue, double regularValue) {
		return "CN".equals(originCountryCode);
	}

	public double calculateTax(double tobaccoValue, double regularValue) {
		// lots of complexity
		return tobaccoValue + regularValue;
	}
}

class EUTaxArea implements TaxArea {
	@Override
	public boolean shouldApplyTo(String originCountryCode, double tobaccoValue, double regularValue) {
		return List.of("RO","ES","FR").contains(originCountryCode);
	}

	public double calculateTax(double tobaccoValue, double regularValue_uselessParam) {
		// a formal contract put in front of classes can lead to a loss in specificity.

		// lots of complexity
		return tobaccoValue / 3;
	}
}
