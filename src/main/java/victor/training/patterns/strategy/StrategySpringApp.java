package victor.training.patterns.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

import static java.lang.System.currentTimeMillis;

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
		TaxCalculator calculator = selectTaxCalculator(originCountry);
		return calculator.computeTax(tobaccoValue, regularValue);
	}

	@Autowired
	List<TaxCalculator> toti;

	private TaxCalculator selectTaxCalculator(String originCountry) {
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
//				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}

		for (TaxCalculator calculator : toti) {
			if (calculator.accepts(originCountry)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("N-am gasit pt tara " + originCountry);
	}
}

interface TaxCalculator {
	boolean accepts(String originCountry);

	double computeTax(double tobaccoValue, double regularValue);
}

@Service
class UKTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "UK" .equals(originCountry);
	}

	public double computeTax(double tobaccoValue, double regularValue) {
		// MAI E treba serioasa 25-27 linii
		return tobaccoValue / 2 + regularValue;
	}
}

//@Service
//class DefaultTaxCalculator implements TaxCalculator {
//	@Override
//	public boolean accepts(String originCountry) {
//		return true;
//	}
//	public double computeTax(double tobaccoValue, double regularValue) {
//		return tobaccoValue  + regularValue;
//	}
//}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return "CN" .equals(originCountry);
	}

	public double computeTax(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

@Service
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean accepts(String originCountry) {
		return List.of("RO", "ES", "FR").contains(originCountry);
	}

	public double computeTax(double tobaccoValue, double regularValue__DEGEABA) {
		return tobaccoValue / 3;
	}
}


class FiltruMeu implements Filter {

	private static final Logger log = LoggerFactory.getLogger(FiltruMeu.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long t0 = currentTimeMillis();

		chain.doFilter(new ServletRequestWrapper(request), response);
		long t1 = currentTimeMillis();
		log.debug("Time took" + (t1 - t0));
	}
}
