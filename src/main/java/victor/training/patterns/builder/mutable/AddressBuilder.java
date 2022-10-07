package victor.training.patterns.builder.mutable;

// AVOID this style of builder (on mutable entities): replace with fluent setters (returning this)
public class AddressBuilder {
	private final Address address = new Address();

	public AddressBuilder withStreetPart(String streetName) {
		address.setStreetName(streetName);
		return this;
	}

	public AddressBuilder withCity(String city) {
		address.setCity(city);
		return this;
	}

	public AddressBuilder withCountry(String country) {
		address.setCountry(country);
		return this;
	}


	public Address build() {
		return address;
	}
}