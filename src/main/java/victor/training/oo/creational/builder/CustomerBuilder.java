package victor.training.oo.creational.builder;

import java.util.Arrays;

public class CustomerBuilder {
	private final Customer customer = new Customer();

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

	public CustomerBuilder withLabels(String... labels) {
		customer.getLabels().addAll(Arrays.asList(labels));
		return this;
	}

	public CustomerBuilder withAddress(AddressBuilder addressBuilder) {
		return withAddress(addressBuilder.build());
	}
}