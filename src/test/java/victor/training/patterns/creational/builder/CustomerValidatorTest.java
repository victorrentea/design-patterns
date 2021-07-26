package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static victor.training.patterns.creational.builder.TestData.aValidCustomer;

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

class TestData { // ObjectMother pattern
	// multe clase de Test vor folosi aceasta metoda.
	public static Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			// intr-o zi cu soare afara dupa o betie cineva vine si  face
			.addLabels("l1") // = 20 teste picate pe jenkins.
			.setAddress(new Address()
				.setCity("Bucharest")
				.setStreetName("Dristor"));
	}
}