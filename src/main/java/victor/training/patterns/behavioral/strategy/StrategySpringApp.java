package victor.training.patterns.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StrategySpringApp.class)
			.profiles("localProps")
			.run(args);
	}

	@Autowired
		CustomsService service;

	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));
	}
}

@Service
class CustomsService {
	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxCalculator taxCalculator = selectCalculatorForCountry(originCountry);
		return taxCalculator.calculate(tobaccoValue, regularValue);
	}

//	Map<String, Class<? extends TaxCalculator>> mapaDinFisierCitita;
	@Autowired
	List<TaxCalculator> toate;
	private TaxCalculator selectCalculatorForCountry(String originCountry) {
		for (TaxCalculator calculator : toate) {
			if (calculator.supports(originCountry)) {
				return calculator;
			}
		}
//		new DefaultTaxCalculator();
		throw new IllegalArgumentException("Not found calculator");
	}

}

interface TaxCalculator {
	boolean supports(String originCountry);
	double calculate(double tobaccoValue, double regularValue);
}
@Service
class ChinaTaxCalculator implements TaxCalculator {
	public boolean supports(String originCountry) {
		return "CN".equals(originCountry);
	}
	public double calculate(double tobaccoValue, double regularValue) {
		// logica
		// logica
		// logica
		// logica
		return tobaccoValue + regularValue;
	}
}
@Service
class UKTaxCalculator implements TaxCalculator {
	@Override
	public boolean supports(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) {
		// un pic mai complicate
		// un pic mai complicate
		return tobaccoValue / 2 + regularValue;
	}
}
@Service
class EUTaxCalculator implements TaxCalculator {
	@Override
	public boolean supports(String originCountry) {
		return List.of("RO","ES", "FR").contains(originCountry);
	}

	public double calculate(double tobaccoValue, double regularValue) { // pret platit de minoritatile dintr-o tara
		return tobaccoValue / 3;
	}
}

@Component
class FiltruMeuDeCafea implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		if (request.header)
		long t0 = currentTimeMillis();
		chain.doFilter(request, response);
		long t1 = currentTimeMillis();
	}
}
@Service
@Order(9999)
class DefaultTaxCalculator implements TaxCalculator{

	@Override
	public boolean supports(String originCountry) {
		return true;
	}

	@Override
	public double calculate(double tobaccoValue, double regularValue) {
		return 0;
	}
}