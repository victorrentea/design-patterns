package victor.training.oo.creational.builder;

import org.junit.Test;

public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	private CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
			.withName("John Doe")
			.withAddress(aValidAddress());
	}

	private AddressBuilder aValidAddress() {
		return new AddressBuilder()
			.withCity("Chisinau")
			.withStreetName("St mare")
			.withCity("Chisinau");
	}

	@Test
	public void validCustomer_ok() {
		Customer customer = aValidCustomer().build();
		validator.validate(customer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsForNullCustomerName() {
		Customer customer = aValidCustomer().withName(null).build();
		validator.validate(customer);
	}
	@Test(expected = IllegalArgumentException.class)
	public void throwsForNullAddressCity() {
		Customer customer = aValidCustomer()
			.withAddress(aValidAddress().withCity(null))
			.build();
		validator.validate(customer);
	}

}