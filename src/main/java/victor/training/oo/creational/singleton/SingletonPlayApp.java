package victor.training.oo.creational.singleton;

import static victor.training.oo.stuff.Helper.workSomeTime;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SingletonPlayApp {
	public static void main(String[] args) {
		SpringApplication.run(SingletonPlayApp.class);
	}
}
@Component
class SingletonPlay implements CommandLineRunner {
	@Autowired
	private AppConfiguration config;
	
	public void run(String... args) throws Exception {
		 // TODO

		// INITIAL(
//		System.out.println("Configuration setting a = " + new AppConfiguration().getProperty("a")); 
//		System.out.println("Configuration setting b = " + new AppConfiguration().getProperty("b")); 
//		System.out.println("Configuration setting a = " + new AppConfiguration().getProperty("a")); 
		// INITIAL)

		// SOLUTION(
		System.out.println("Configuration setting a = " + config.getProperty("a")); 
		System.out.println("Configuration setting b = " + config.getProperty("b")); 
		System.out.println("Configuration setting a = " + config.getProperty("a")); 
		// SOLUTION)

	}
}

@Service
class AppConfiguration {

	private Properties properties;
	
	@PostConstruct
	public void init() {
		properties = readConfiguration();
	}

	private Properties readConfiguration() {
		System.out.println("Fetching encrypted configuration over HTTPS from Thailand");
		workSomeTime();
		Properties props = new Properties();
		try {
			props.load(AppConfiguration.class.getResourceAsStream("dummy.properties"));
			System.out.println("Done");
			return props;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

}