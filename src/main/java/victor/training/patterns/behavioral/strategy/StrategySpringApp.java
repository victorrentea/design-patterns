package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
//		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("MX", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

@Service
class CustomsService {
	private static final Map<?, TaxCalculator> MAP = Map.of(
		"UK", new UKTaxCalculator(),
		"CN", new ChinaTaxCalculator(),
		"BE", new EUTaxCalculator()
		//47 countries ==> externalize in a property file. >> here MAP WINS!
	);

	static {
//		MAP = readFromProperties
	}

	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
//		return MAP.get(originCountry).calculateTax(tobaccoValue, regularValue);
//		MAP.compute(originCountry, (k,b) -> ); // TODO

		TaxCalculator calculator = selectCalculator(originCountry);
		return calculator.calculateTax(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxCalculator> all;// = List.of(new EUTaxCalculator(), new ChinaTaxCalculator(), new UKTaxCalculator());

	private TaxCalculator selectCalculator(String originCountry) {

		for (TaxCalculator taxCalculator : all) {
			if (taxCalculator.accepts(originCountry)) {
				return taxCalculator;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);

//		switch (originCountry) {
//			case "UK":
//				return new UKTaxCalculator();
//			case "CN":
//				return new ChinaTaxCalculator();
//			case "FR":
//			case "BE":
//			case "NL":
//			case "ES": // other EU country codes...
//			case "RO":
//				return new EUTaxCalculator();
//			default:
//				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean accepts(String originCountry) {
		return "CN".equals(originCountry);
	}
}

@Component
@Order(19919319)
class DefaultTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}

	@Override
	public boolean accepts(String originCountry) {
		return true;
	}
}

@Component
class UKTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValue) {
		// heavy logic
		return tobaccoValue / 2 + regularValue;
	}

	@Override
	public boolean accepts(String originCountry) {
		return "UK".equals(originCountry);
	}
}

@Component
class EUTaxCalculator implements TaxCalculator {
	public double calculateTax(double tobaccoValue, double regularValueUNUSED_WHY_IT_THIS_HERE_YOU_WONDER_IN_2_YEARS) { // loss of precision.
		return tobaccoValue / 3;
	}

	@Override
	public boolean accepts(String originCountry) {
		return List.of("RO", "ES", "FR", "BE", "NL").contains(originCountry);
	}
}

interface TaxCalculator {
	double calculateTax(double tobaccoValue, double regularValue);

	boolean accepts(String originCountry);
}

@Component
class MyFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getHeader("Weird") == null) {
			throw new IllegalArgumentException("OUT!");
		}
		ServletRequestWrapper servletRequestWrapper = new ServletRequestWrapper(request) {
			@Override
			public String getParameter(String name) {
				if (name.equals("thatparam")) return "WAS ON REQUEST (you lie)";
				return super.getParameter(name);
			}
		};


		chain.doFilter(servletRequestWrapper, response);
		// audit measure
	}
}