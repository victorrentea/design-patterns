package victor.training.patterns.facade;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.training.patterns.facade.facade.CustomerFacade;
import victor.training.patterns.facade.facade.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class FacadeSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(FacadeSpringApp.class);
	}
	@Autowired
	private CustomerFacade customerFacade;

	/**
	 *
	 * @param args incoming main method arguments
	 * @throws Exception
	 */
	public void run(String... args) throws Exception {
		//LIBRARIE are mici metode utilitare pe care TU developer le chemi cand vrei tu
		boolean b = StringUtils.isBlank(" ");
		System.out.println(b);

		customerFacade.register2(new CustomerDto("John " +
												  "" +
												  "Doe", "john.doe@pentagon.us"));
	}

	@GetMapping("/craciun") // Spring Framework simte ac adnotare si daca te duci
	// la locahost:8080/craciun o sa vezi in bro "Cadou"
	public String cereSiTisevada() {
		return "Cadou";
	}

	public void method(List<String> elemente) {

	}
}
