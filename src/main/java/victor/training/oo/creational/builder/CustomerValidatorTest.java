package victor.training.oo.creational.builder;

import org.junit.Test;

// Object Mother  pattern
class DummyData {

	public static Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			.addLabels("label1","label2")
			.setAddress(aValidAddress());
	}

	public static Address aValidAddress() {
		return new Address()
			.setCity("Bucharest");
	}
}
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		validator.validate(DummyData.aValidCustomer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsForEmptyName() {
		Customer customer = DummyData.aValidCustomer().setName(null);
		validator.validate(customer);
	}
	@Test(expected = IllegalArgumentException.class)
	public void throwsForEmptyAdressCity() {
		Customer customer = DummyData.aValidCustomer()
			.setAddress(DummyData.aValidAddress().setCity(null));

		validator.validate(customer);
	}

}