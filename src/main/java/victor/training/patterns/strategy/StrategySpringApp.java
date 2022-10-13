package victor.training.patterns.strategy;

import lombok.Data;
import org.mockito.internal.verification.api.InOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

	@Autowired
	private CustomsService service;
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
//		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
//	private Map<String, Class<? extends ITaxCalculator>> calculators;  // hehe



	public double calculateCustomsTax(String originCountry,
									  double tobaccoValue,
									  double regularValue) { // UGLY API we CANNOT change
		ITaxCalculator taxCalculator = selectCalculator(originCountry);
		return taxCalculator.compute(tobaccoValue,regularValue);
	}

	// switchu are 3 reguli:
	// 1) sta singur in metoda
	// 2) are default thnrtow (JDD)
	// 3) in case 1-2-3 lini -> extract methoda
	private ITaxCalculator selectCalculator(String countryIso) {
		System.out.println("Calculatiorii toti: " + taxCalculators);
		return taxCalculators.stream()
				.filter(tc -> tc.appliesFor(countryIso, LocalDate.now()))
				.findFirst() // hmmmmmm???
				.orElseThrow();
	}

	@Autowired
	private List<ITaxCalculator> taxCalculators;
}
interface DateDeFiltrare {

}
class DateleToateCaz1 implements DateDeFiltrare {

}
class DateleToateCaz2 implements DateDeFiltrare {

}


interface ITaxCalculator {
//	int getPriority();
	boolean appliesFor(String countryIso, LocalDate entryDate);
	public double compute(double tobaccoValue, double regularValue);
}
@Service
class ChinaTaxCalculator2023 implements ITaxCalculator{
//	@Override
//	public int getPriority() {
//		return 1;
//	}

	@Override
	public boolean appliesFor(String countryIso, LocalDate entryDate) {
		return entryDate.getYear() == 2023 && countryIso.equals("CN");
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
@Service
class BrexitTaxCalculator implements ITaxCalculator{
//	@Override
//	public int getPriority() { // cuplare cu toti ceilalti. mai bine configurezi din exterior ordinea
//		return 20;
//	}
	@Override
	public boolean appliesFor(String countryIso, LocalDate entryDate) {
		return "UK".equals(countryIso);
	}

	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue / 2 + regularValue;
	}

}
@Service
class EUTaxCalculator implements ITaxCalculator{
	@Override
	public boolean appliesFor(String countryIso, LocalDate entryDate) {
		return List.of("RO", "ES", "FR").contains(countryIso);
	}

	public double compute(double tobaccoValue, double degeabaValue) {
		return tobaccoValue / 3;
	}
}
@Service
class DefaultTaxCalculator implements ITaxCalculator{
	@Override
	public boolean appliesFor(String countryIso, LocalDate entryDate) {
		return true;
	}

	public double compute(double tobaccoValue, double degeabaValue) {
		return  0;
	}
}