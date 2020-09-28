package victor.training.oo.creational.builder;

import org.junit.Test;

import static victor.training.oo.creational.builder.DummyTestData.aValidAddress;
import static victor.training.oo.creational.builder.DummyTestData.aValidCustomer;

// Object Mother F..
// cand prea multe teste depind de clasa asta, tai-o in bucati.
class DummyTestData {

	public static CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
			.withName("John Doe")
			.withAddress(aValidAddress());
	}

	public static AddressBuilder aValidAddress() {
		return new AddressBuilder()
			.withCity("Chisinau")
			.withStreetName(""); // colegu modifica instanta dummy folosita de 100 de teste.
	}
}

public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();


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