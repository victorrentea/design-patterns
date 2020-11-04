package victor.training.patterns.creational.builder;

public class Address {
	private String streetAddress;
	private String city;
	private String country;

	public String getStreetAddress() {
		return streetAddress;
	}

	public Address setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Address setCountry(String country) {
		this.country = country;
		return this;
	}
}
