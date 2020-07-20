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
		switch (originCountry) { 
		case "UK": return new UKCustomsComputer().compute(tobaccoValue, regularValue);

		case "CN": 	return new ChinaCustomsComputer().compute(tobaccoValue, regularValue);

		case "FR":
		case "ES": // other EU country codes...
		case "RO": return new EUCustomsComputer().compute(tobaccoValue);
		default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		} 
	}
}

class ChinaCustomsComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// 50 linii cod
		return tobaccoValue + regularValue;
	}
}
class UKCustomsComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// Gabi: las si io asta aici // 30 linii cod
		// Maria: pun si io if-u asta, ca n-am unde sa-l las
		return tobaccoValue/2 + regularValue;
	}
}
class EUCustomsComputer {
	public double compute(double tobaccoValue) {
		// 50 linii cod
		return tobaccoValue/3;
	}
}
