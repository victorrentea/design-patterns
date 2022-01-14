package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

	@Autowired
	CustomsService service;

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	// TODO [4] ConfigProvider: selected based on environment props, with Spring
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
		TaxCalculator taxCalculator = selectTaxCalculator(originCountry);
		return taxCalculator.compute(tobaccoValue, regularValue);
	}

	//	Map<String, Class<? extends  TaxCalculator>> countryCodeToTaxImpl; // read id from a yaml/properties file.
	@Autowired
	List<TaxCalculator> allCalculator;

	private TaxCalculator selectTaxCalculator(String originCountry) {
		for (TaxCalculator calculator : allCalculator) {
			if (calculator.isApplicable(originCountry)) {
				return calculator;
			}
		}

//		switch (originCountry) {
//			case "UK":
//				return new BrexitTaxCalculator();
//			case "CN":
//				return new ChinaTaxCalculator();
//			case "FR":
//			case "BE":
//			case "ES": // other EU country codes...
//			case "RO":
//				return new EUTaxCalculator();
//			default:
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//		}
	}
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String countryCode) {
		return "CN".equals(countryCode);
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}

@Component
class BrexitTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String countryCode) {
		return "UK".equals(countryCode);
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}
}

@Component
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean isApplicable(String countryCode) {
		return List.of("RO", "ES", "FR", "BE").contains(countryCode);
	}

	public double compute(double tobaccoValue, double regularValueUnused) {
		return tobaccoValue / 3;
	}


}

interface TaxCalculator {
	boolean isApplicable(String countryCode);

	double compute(double tobaccoValue, double regularValue);
}


class MyFilterLikeIn2008 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequst = (HttpServletRequest) request;
//		httpRequst.getHeader("Authorization")
		chain.doFilter(request, response);
	}
}