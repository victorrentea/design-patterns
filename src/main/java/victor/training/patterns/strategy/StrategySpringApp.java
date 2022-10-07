package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

	@Autowired
	private CustomsService service;
	// TODO [1] Break CustomsService logic into Strategies
	// TODO [2] Convert it to Chain Of Responsibility
	// TODO [3] Wire with Spring
	public void run(String... args) {
		System.out.println("Tax for (RO,100,100) = " + service.calculateCustomsTax("RO", 100, 100));
		System.out.println("Tax for (CN,100,100) = " + service.calculateCustomsTax("CN", 100, 100));
		System.out.println("Tax for (UK,100,100) = " + service.calculateCustomsTax("UK", 100, 100));

		System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
	}
}
@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
//	private Map<String, TaxCalculator> calculators;  // hehe

	public double calculateCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
		RestTemplate r = new RestTemplate();
		switch (originCountry) {
			case "UK":
				return tobaccoValue / 2 + regularValue;
			case "CN":
				return tobaccoValue + regularValue;
			case "FR":
			case "ES": // other EU country codes...
			case "RO":
				return tobaccoValue / 3;
			default:
				throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
		}
	}
}
