package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
//	@HandlerFor("UK")
	public static double computeUK(double tobaccoValue, double regularValue) {
		return 1;
	}

//	@HandlerFor("CN")
	public static double computeCN(double tobaccoValue, double regularValue) {
		return 1;
	}

//	@HandlerFor({"FR","ES","RO"})
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


@Component
class MyFilterLike10YearsAgo implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//stuff before (tweaking or checking the request)
		chain.doFilter(request, response);
		// stuff after the http request handling is finished
	}
}

//@FunctionalInterface
//interface RequestInspector {
//	Optional<RequestProcessor> getProcessor(HttpServletRequest request);
//}
//
//
//interface RequestProcessor {
//
//	String getRequestId();
//
//	void storeRequestId(String requestId);
//
//	default void execute() {
//		storeRequestId(getRequestId());
//	}
//}
//
//class Explore {
//	public static void main(String[] args) {
//		HttpServletRequest request = null;
//		RequestInspector inspector;
//		RequestProcessor requestProcessor = new Xyy().getProcessor(request)
//			.or(() -> new Xyy2().getProcessor(request))
//			.or(() -> new Xyy3().getProcessor(request))
//			.orElse(new DefaultProcessor());
//
//		requestProcessor.getRequestId()
//	}
//}
//
//class Xyy implements RequestInspector {}