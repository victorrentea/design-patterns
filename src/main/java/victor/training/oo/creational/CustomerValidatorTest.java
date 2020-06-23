package victor.training.oo.creational;

import org.junit.Test;
import victor.training.oo.creational.builder.Address;
import victor.training.oo.creational.builder.Customer;
import victor.training.oo.creational.builder.CustomerValidator;

public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();
	
	@Test
	public void validCustomer_ok() {
//		new Customer();
		Customer customer =
			new Customer("asd");
		customer.setName(null);
//		customer.setName("John Doe");
		Address address = new Address();
		address.setCity("Bucharest");
		customer.setAddress(address);
		validator.validate(customer);
	}

}