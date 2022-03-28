package victor.training.patterns.creational.builder;

public class AddressBuilder {
	private final Address address = new Address();

	public AddressBuilder withStreetName(String streetName) {
		address.setStreetName(streetName);
		return this;
	}

	public AddressBuilder withCity(String city) {
		address.setCity(city);
		return this;
	}

	public Address build() {
		return address;
	}
}