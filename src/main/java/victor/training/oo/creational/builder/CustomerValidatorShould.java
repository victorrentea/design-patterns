package victor.training.oo.creational.builder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import victor.training.oo.creational.builder.CustomerValidator.CustomerWithoutNameException;

public class CustomerValidatorShould {

	private CustomerValidator validator = new CustomerValidator();

	@Test
	public void beFineForValidCustomer() {
		validator.validate(aValidCustomer()
				.build());
	}

	private CustomerBuilder aValidCustomer() {
		return new CustomerBuilder()
				.withName("John Doe")
				.withAddress(new AddressBuilder()
						.withCity("Bucharest")
						.build());
	}
	
	@Test(expected = CustomerWithoutNameException.class)
	public void throwForCustomerWithoutName() {
		validator.validate(aValidCustomer().withName(null).build());
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test(expected = IllegalArgumentException.class)
	public void throwForCustomerWithoutAddress() {
		expectedException.expectMessage("address");
//		expectedException.expect(hamcrestMatcher(ErrorCode.CUSTOMER_WITOUT_ADDRESS));
		validator.validate(aValidCustomer().withAddress(null).build());
	}
	
	
	
}