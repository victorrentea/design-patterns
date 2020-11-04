package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void validCustomer_ok() {
		Customer customer = aValidCustomer().build();
		validator.validate(customer);
	}

	private CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
			.withName("John Doe")
			.withAddress(aValidAddress()
				.build());
	}

	private AddressBuilder aValidAddress() {
		return new AddressBuilder()
			.withCity("Bucharest")
			.withStreetName("Dristor");
	}

	@Test
	public void throwsForCustomerWithNoName() {
		Customer customer = aValidCustomer().withName(null).build();
		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}

	@Test
	public void throwsForCustomerWithNoAddressCity() {
		Customer customer = aValidCustomer()
			.withAddress(aValidAddress().withCity(null).build())
			.build();

		Assertions.assertThrows(IllegalArgumentException.class,
			() -> validator.validate(customer));
	}


















}