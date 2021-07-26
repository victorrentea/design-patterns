package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void validCustomer_ok() {
		Customer customer = aValidCustomer();
		validator.validate(customer);
	}

	private Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			.setAddress(new Address()
				.setCity("Bucharest")
				.setStreetName("Dristor"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void customerWithoutName() {
		Customer customer = aValidCustomer().setName(null);
		validator.validate(customer);
	}

	@Test
	public void customerWithoutCity() {
		expectedException.expectMessage("city");
		Customer customer = aValidCustomer();
		customer.getAddress().setCity(null);
		validator.validate(customer);
	}

	@Test
	public void customerWithoutStreeName() {
		expectedException.expectMessage("street");
		Customer customer = aValidCustomer();
		customer.getAddress().setStreetName(null);
		validator.validate(customer);
	}

}