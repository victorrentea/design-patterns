package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator = selectCalculatorFor(originCountry);
		return taxCalculator.compute(tobaccoValue, regularValue);
	}

//	private static final Map<String, TaxCalculator> calculators =
////		readFromFile("tax-calculators.properties"); // OK
//		Map.of(
//			"UK", new UKTaxCalculator(),
//			"CN", new ChinaTaxCalculator(),
//			"FR", new EUTaxCalculator(),
//			"ES", new EUTaxCalculator(),
//			"RO", new EUTaxCalculator()
//		);


	private TaxCalculator selectCalculatorFor(String originCountry) { // factory method
		List<TaxCalculator> toate = List.of(new UKTaxCalculator(), new ChinaTaxCalculator(), new EUTaxCalculator());
		// cum poti gasi toate impl
		// A) le listezi intr-un fisier (eg web.xml) > poti controla ORDINEA (eg sa permiti un DefaultHandler sa vina ultimul)
		// B) scanare de clase la startup (cum face springul) - toate care implem interfata
		// daca gasesi in classpath iei in calcul.

		for (TaxCalculator calculator : toate) {
			if (calculator.canComputeFor(originCountry)) {
				return calculator;
			}
		}

//		return calculators.get(originCountry); // intoarce NULL daca nu gaseste.

//		switch (originCountry) {
//			case "UK":
//				return new UKTaxCalculator();
//			case "CN":
//				return new ChinaTaxCalculator();
//			case "FR":
//			case "ES": // other EU country codes...
//			case "RO":
//				return new EUTaxCalculator();
//			default:
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}
}

class ChinaTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		// complex
		return tobaccoValue + regularValue;
	}

	public boolean canComputeFor(String originCountry) {
		return "CN".equals(originCountry);
	}
}
//class DefaultTaxCalculator implements TaxCalculator {
//	public double compute(double tobaccoValue, double regularValue) {
//		// complex
//		return tobaccoValue + regularValue;
//	}
//
//	public boolean canComputeFor(String originCountry) {
//		return true;
//	}
//}

class UKTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValue) {
		// complex
		return tobaccoValue / 2 + regularValue;
	}

	public boolean canComputeFor(String originCountry) {
		return "UK".equals(originCountry);
	}
}

class EUTaxCalculator implements TaxCalculator {
	public double compute(double tobaccoValue, double regularValueUnused) { // pierdere de specificitate. am luat un param degeaba
		// complex
		return tobaccoValue / 3;
	}

	@Override
	public boolean canComputeFor(String originCountry) {
		return List.of("RO", "FR", "ES").contains(originCountry);
	}
}

interface TaxCalculator {
	double compute(double tobaccoValue, double regularValue);

	boolean canComputeFor(String originCountry);
}


class MyFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String header = httpRequest.getHeader("Authorization");
//		if (!valid) throw new IllegalArgumentException("not authorized");
		chain.doFilter(request, response);
		// dupa handlingul requestului aici
	}
}
