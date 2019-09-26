package victor.training.oo.creational.builder;

class CustomerBuilder {
	private final Customer customer = new Customer();

	public CustomerBuilder withName(String name) {
		customer.setName(name);
		return this;
	}


	public Customer build() {
		return customer;
	}


	public CustomerBuilder addLabel(String label) {
		customer.getLabels().add(label);
		return this;
	}


	public CustomerBuilder withAddress(Address address) {
		customer.setAddress(address);
		return this; 
	}
}