package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	private Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			.setAddress(aValidAddress());
	}

	private Address aValidAddress() {
		return new Address()
			.setCity("Bucharest")
			.setStreetAddress("Dristor");
	}

	@Test
	public void validCustomer_ok() {
		Customer customer = aValidCustomer();
		validator.validate(customer);
	}

	@Test
	public void throwsForCustomerWithNoName() {
		Customer customer = aValidCustomer().setName(null);
		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}

	@Test
	public void throwsForCustomerWithNoAddressCity() {
		Customer customer = aValidCustomer()
			.setAddress(aValidAddress().setCity(null));

		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}


















}