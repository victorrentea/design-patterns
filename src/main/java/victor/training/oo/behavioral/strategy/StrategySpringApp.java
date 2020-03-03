package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		TaxComputer computer = selectComputer(originCountry);
		return computer.compute(tobaccoValue, regularValue);
	}

	private TaxComputer selectComputer(String originCountry) {
		// other EU country codes...
		switch (originCountry) {
			case "UK":
				return new UKTaxComputer();
			case "CN":
				return new ChinaTaxComputer();
			case "FR":
			case "ES":
			case "RO":
				return new EUTaxComputer();
			default:
				throw new IllegalStateException("Unexpected value: " + originCountry);
		}
	}
//	enum E {
//		A,B,C
//	}
//	public String m(E e) {
//	    return switch (e) {
//			case A ->"a";
//			case B->"b";
//			case C -> "c";
//		};
//	}
}
interface TaxComputer {
	double compute(double tobaccoValue, double regularValue);
}
class UKTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// Gigel: pun si io aicea astea 200de linii de cod si un if intautru si paote alt switch inauntru
		return tobaccoValue / 2 + regularValue;
	}
}
class ChinaTaxComputer implements TaxComputer {
	public double compute(double tobaccoValue, double regularValue) {
		return tobaccoValue + regularValue;
	}
}
class ChinaExtraLogic {
	private final ChinaTaxComputer computer;

	ChinaExtraLogic(ChinaTaxComputer computer) {
		this.computer = computer;
	}
	public void logicaExtra() {

	}
}
class EUTaxComputer implements TaxComputer {
	@Override
	public double compute(double tobaccoValue, double regularValue) {
		// Maricica:
		return tobaccoValue / 3;
	}
}
