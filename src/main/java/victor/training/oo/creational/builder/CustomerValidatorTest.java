package victor.training.oo.creational.builder;

import org.junit.Test;

public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();
	
	@Test
	public void validCustomer_ok() {
		Customer customer = new Customer();
		customer.setName("John Doe");
		Address address = new Address();
		address.setCity("Bucharest");
		customer.setAddress(address);
		validator.validate(customer);
	}

}