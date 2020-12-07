package victor.training.patterns.creational.builder;

import static java.util.Arrays.asList;

public class CustomerBuilder {
	private final Customer customer = new Customer();

	public CustomerBuilder withName(String name) {
		customer.setName(name);
		return this;
	}

	public Customer build() {
		return customer;
	}
//	public Customer persist() {
//		return magic iau entity manger, .persist;
//	}

	public CustomerBuilder withAddress(Address address) {
		customer.setAddress(address);
		return this;
	}

	public CustomerBuilder addLabel(String... labels) {
		customer.getLabels().addAll(asList(labels));
		return this;
	}

	public CustomerBuilder withAddress(AddressBuilder builder) {
		return withAddress(builder.build());
	}
}