package victor.training.patterns.creational.builder;

import java.util.List;

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

	public CustomerBuilder addLabel(String ... labels) {
		customer.getLabels().addAll(asList(labels));
		return this;
	}

	public CustomerBuilder setAddress(Address address) {
		customer.setAddress(address);
		return this;
	}
}