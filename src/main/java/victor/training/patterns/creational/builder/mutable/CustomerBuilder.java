package victor.training.patterns.creational.builder.mutable;

import java.util.List;

public class CustomerBuilder {
	private final Customer customer = new Customer();

	public CustomerBuilder withName(String name) {
		customer.setName(name);
		return this;
	}

	public CustomerBuilder withLabels(List<String> labels) {
		customer.setLabels(labels);
		return this;
	}

	public Customer build() {
		return customer;
	}

	public CustomerBuilder withAddress(Address address) {
		customer.setAddress(address);
		return this;
	}
}