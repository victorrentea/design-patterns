package victor.training.oo.creational.builder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();
	
	private CustomerValidator customerValidator = new CustomerValidator();
	@Test
	public void validCustomer_ok() {
		customerValidator.validate(aValidCustomer().build());
		
	}
	private CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
				.withName("John Doe")
				.withAddress(new AddressBuilder()
						.withCity("Bucale")
						.build());
	}
	@Test(expected = IllegalArgumentException.class)
	public void throwsForCustomerWithNoName() {
		customerValidator.validate(aValidCustomer().withName(null).build());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwsForCustomerWithNoCity() {
		Customer customer = new CustomerBuilder()
				.withName("John Doe")
				.withAddress(new AddressBuilder()
						.build())
				.build();
		
		new CustomerValidator().validate(customer);
	}
}