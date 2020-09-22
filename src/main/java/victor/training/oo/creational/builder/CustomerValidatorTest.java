package victor.training.oo.creational.builder;

import org.junit.Test;

// Object Mother  pattern
class DummyData {

	public static CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
			.withName("John Doe")
			.withAddress(aValidAddress()
				.build());
	}

	public static AddressBuilder aValidAddress() {
		return new AddressBuilder()
			.withCity("Bucharest")
			;
	}
}
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		validator.validate(DummyData.aValidCustomer().build());
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsForEmptyName() {
		Customer customer = DummyData.aValidCustomer().withName(null).build();
		validator.validate(customer);
	}
	@Test(expected = IllegalArgumentException.class)
	public void throwsForEmptyAdressCity() {
		Customer customer = DummyData.aValidCustomer()
			.withAddress(DummyData.aValidAddress().withCity(null)).build();

		validator.validate(customer);
	}

}