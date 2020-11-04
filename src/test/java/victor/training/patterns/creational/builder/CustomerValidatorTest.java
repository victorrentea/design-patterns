package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// other test classes are using these test data factory methods.

class TestData {
	// every test class uses these.
	public static Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			.setName("John Doe")
			.setPhone("")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setName("John Doe")
			.setAddress(aValidAddress());
	}

	public static Address aValidAddress() {
		return new Address()
			.setCity("Bucharest")
			.setStreetAddress("Dristor");
	}
}

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		Customer customer = TestData.aValidCustomer();
		validator.validate(customer);
	}

	@Test
	public void throwsForCustomerWithNoName() {
		Customer customer = TestData.aValidCustomer().setName(null);
		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}

	@Test
	public void throwsForCustomerWithNoAddressCity() {
		Customer customer = TestData.aValidCustomer()
			.setAddress(TestData.aValidAddress().setCity(null));

		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}


















}