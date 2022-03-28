package victor.training.patterns.creational.builder;

import java.util.Objects;


public class Address {
	private final String streetName;
	private final String city;
	private final String country;
	private final Integer streetNumber; // optional field

	// minimal constructor
	public Address(String streetName, String city, String country) {
		this(streetName, city, country, null); //all other constructors delegate to the canonical one
	}

	// canonical constructor
	public Address(String streetName, String city, String country, Integer streetNumber) {
		this.streetName = Objects.requireNonNull(streetName); // good practice: validation in constructor
		this.city = Objects.requireNonNull(city);
		this.country = Objects.requireNonNull(country);
		this.streetNumber = streetNumber;
	}

	// "wither" pattern returns a modified copy of the immutable object.
	public Address withStreetNumber(int streetNumber) {
//		this.streetNumber = streetNumber; // NO > makes this class mutable
//		return this;
		return new Address(streetName, city, country, streetNumber);
	}

	public String getStreetName() {
		return this.streetName;
	}

	public String getCity() {
		return this.city;
	}

	public String getCountry() {
		return this.country;
	}

	public Integer getStreetNumber() {
		return this.streetNumber;
	}


	public String toString() {
		return "Address(streetName=" + this.getStreetName() + ", city=" + this.getCity() + ", country=" + this.getCountry() + ", streetNumber=" + this.getStreetNumber() + ")";
	}
}
