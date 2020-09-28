package victor.training.oo.creational.builder;

import org.junit.Test;

import static victor.training.oo.creational.builder.DummyTestData.aValidAddress;
import static victor.training.oo.creational.builder.DummyTestData.aValidCustomer;

// Object Mother F..
// cand prea multe teste depind de clasa asta, tai-o in bucati.
class DummyTestData {

	public static Customer aValidCustomer() {
		return new Customer()
			.setName("John Doe")
			.setAddress(aValidAddress());
	}

	public static Address aValidAddress() {
		return new Address()
			.setCity("Chisinau")
			.setStreetName(""); // colegu modifica instanta dummy folosita de 100 de teste.
	}
}
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		Customer customer = aValidCustomer();
		validator.validate(customer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsForNullCustomerName() {
		Customer customer = aValidCustomer().setName(null)
			.addLabels("Label");
		validator.validate(customer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsForNullAddressCity() {
		Customer customer = aValidCustomer()
			.setAddress(aValidAddress().setCity(null));
		validator.validate(customer);
	}

}