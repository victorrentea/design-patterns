package victor.training.oo.creational.builder;

import org.junit.Test;

public class CustomerValidatorTest {

    private CustomerValidator validator = new CustomerValidator();

    private Customer aValidCustomer() {
        return new Customer()
                .setName("John Doe")
                .setAddress(aValidAddress());
    }

	private Address aValidAddress() {
		return new Address()
				.setCity("Bucharest");
	}

	@Test
    public void validCustomer_ok() {
        Customer customer = aValidCustomer();
        validator.validate(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForEmptyName() {
        Customer customer = aValidCustomer().setName(null);
        validator.validate(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForNullAddress() {
        Customer customer = aValidCustomer().setAddress(null);
        validator.validate(customer);
    }
    @Test(expected = IllegalArgumentException.class)
    public void throwsForNullCity() {
        Customer customer = aValidCustomer()
				.setAddress(aValidAddress().setCity(null));
        validator.validate(customer);
    }

}

//class ObjectMother {
//	public static Customer aValidCustomer() { // 60 de folosire
//		return new Customer()
//				.setName("John Doe")
////				.setTitle("Babacu'")
//				.setAddress(aValidAddress());
//	}
//
//	public static Address aValidAddress() {
//		return new Address()
//				.setCity("Bucharest");
//	}
//}