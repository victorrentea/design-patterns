package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		Customer customer = new Customer();
		customer.setName("John Doe");
		Address address = new Address();
		address.setCity("Bucharest");
		address.setStreetName("Dristor 91");
		customer.setAddress(address);
		validator.validate(customer);
	}

}