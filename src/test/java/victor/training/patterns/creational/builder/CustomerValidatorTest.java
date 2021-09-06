package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Customer customer = TestData.aValidCustomer().setName(null);
			validator.validate(customer);
		});
	}

}

class TestData { // Object Mother

	public static Customer aValidCustomer() { // used in 167 tests
		return new Customer()
			.setName("John")
			.setAddress(new Address()
				.setCity("Bucharest")
				.setStreetName("Dristor 91"));
	}
}