package victor.training.patterns.creational.builder;

import static java.util.Arrays.asList;

public class CustomerBuilder {
	private final Customer customer = new Customer();

	public CustomerBuilder withName(String name) {
		customer.setFullName(name);
		return this;
	}

	public Customer build() {
		return customer;
	}

	public CustomerBuilder addLabel(String... labels) {
		customer.getLabels().addAll(asList(labels));
		return this;
	}
	public void persist() {
		// IN TESTS: magically get a datasource/entity manager and persist this to DB. 
	}

	public CustomerBuilder withAddress(Address address) {
		customer.setAddress(address);
		return this;
	}

	public CustomerBuilder withAddress(AddressBuilder addressBuilder) {
		return withAddress(addressBuilder.build());
	}
}