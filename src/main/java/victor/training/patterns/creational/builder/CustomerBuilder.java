package victor.training.patterns.creational.builder;

public class CustomerBuilder {
	private final Customer customer = new Customer();

	public CustomerBuilder withName(String name) {
		customer.setName(name);
		return this;
	}

	public Customer build() {
//		if (customer.getName().length() <= 5) {
//			throw new IllegalArgumentException("Name too short");
//		}
		return customer;
	}
}