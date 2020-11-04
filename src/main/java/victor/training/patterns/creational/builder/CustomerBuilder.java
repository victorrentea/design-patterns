package victor.training.patterns.creational.builder;

public class CustomerBuilder {
	private final Customer customer = new Customer(); // under construction

	public CustomerBuilder withName(String name) {
		customer.setName(name);
		return this;
	}

	public Customer build() {
		return customer;
	}

   public CustomerBuilder withAddress(Address address) {
		customer.setAddress(address);
		return this;
   }
   public CustomerBuilder withAddress(AddressBuilder addressBuilder) {
		return withAddress(addressBuilder.build());
   }
}