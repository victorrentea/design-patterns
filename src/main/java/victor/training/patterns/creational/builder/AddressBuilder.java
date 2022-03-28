package victor.training.patterns.creational.builder;

public class AddressBuilder {
	private String streetName;
	private String city;
	private String country;
	private Integer streetNumber;

	public AddressBuilder withStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}

	public AddressBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	public AddressBuilder withCountry(String country) {
		this.country = country;
		return this;
	}

	public AddressBuilder withStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
		return this;
	}

	public Address build() {
		return new Address(streetName, city, country, streetNumber);
	}
}