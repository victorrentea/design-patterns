package victor.training.patterns.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import victor.training.patterns.facade.facade.CustomerFacade;
import victor.training.patterns.facade.facade.dto.CustomerDto;

@SpringBootApplication
//@Enable....(order = 4)
@EnableTransactionManagement(order = 3)
public class FacadeSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(FacadeSpringApp.class);
	}
	@Autowired
	private CustomerFacade customerFacade;
	
	public void run(String... args) throws Exception {
		// pretend you are in a @RestController here
		customerFacade.register(new CustomerDto("John Doe", "john.doe@pentagon.us"));
	}
}
