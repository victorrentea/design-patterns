package victor.training.oo.structural.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import victor.training.oo.structural.facade.facade.CustomerFacade;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;

@SpringBootApplication
public class FacadeSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(FacadeSpringApp.class);
	}
	@Autowired
	private CustomerFacade customerFacade;
	
	public void run(String... args) throws Exception {
		// pretend you are in a @RestController here
		customerFacade.registerCustomer(new CustomerDto("John Doe", "john.doe@pentagon.us"));
	}
}

@RestController
class Contr {
	@GetMapping
	public String help() {
		return "asad";
	}
}
