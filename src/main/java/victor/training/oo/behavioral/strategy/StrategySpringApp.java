package victor.training.oo.behavioral.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
	@Autowired
	CustomsService service;
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));
		
		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
class CustomsService {
	@Autowired
	private List<CustomsComputer> all;

	public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		CustomsComputer customsComputer = selectCustomsComputer(originCountry);
		return customsComputer.compute(tobaccoValue, regularValue);
	}

	private CustomsComputer selectCustomsComputer(String originCountry) {
		for (CustomsComputer computer : all) {
			if (computer.canHandleCountry(originCountry)) {
				return computer;
			}
		}
		throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry); // 1
	}
}

interface CustomsComputer {
	double compute(double tobaccoValue, double regularValue);
	boolean canHandleCountry(String originCountry); // 2
}

@Service
class ChinaCustomsComputer implements CustomsComputer {
	public double compute(double tobaccoValue, double regularValue) {
		// 50 linii cod
		return tobaccoValue + regularValue;
	}
	@Override
	public boolean canHandleCountry(String originCountry) {
		return "CN".equals(originCountry);
	}

}
@Service
class UKCustomsComputer implements CustomsComputer {
	private String originCountryCode;

	public double compute(double tobaccoValue, double regularValue) {
		// Gabi: las si io asta aici // 30 linii cod
		// Maria: pun si io if-u asta, ca n-am unde sa-l las
		return tobaccoValue/2 + regularValue;
	}

	@Override
	public boolean canHandleCountry(String originCountry) {
		return "UK".equals(originCountry);
	}
}
@Service
class EUCustomsComputer implements CustomsComputer { // Tirania majoritatii - UN MINUS
	@Override
	public double compute(double tobaccoValue, double regularValue) { // --- iau regular value degeaba param. Sa-i traiasca familia lu mentenatoru'
		// 50 linii cod
		return tobaccoValue/3;
	}

	@Override
	public boolean canHandleCountry(String originCountry) {
		return Arrays.asList("RO","ES","FR").contains(originCountry);
	}
}
