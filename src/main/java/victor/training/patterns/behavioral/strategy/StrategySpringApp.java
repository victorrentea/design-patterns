package victor.training.patterns.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class StrategySpringApp  {
	public static void main(String[] args) {
		CustomsService service = new CustomsService();
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
	}
}

class CustomsService {
	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		switch (originCountry) { 
		case "UK": return tobaccoValue/2 + regularValue;
		case "CN": return tobaccoValue + regularValue;
		case "FR": 
		case "ES": // other EU country codes...
		case "RO": return tobaccoValue/3;
		default: throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		} 
	}
}
