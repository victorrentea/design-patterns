package victor.training.patterns.builder.mutable;

public class Address {
	private String streetName;
	private String city;
	private String country;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address{" +
				 "streetName='" + streetName + '\'' +
				 ", city='" + city + '\'' +
				 ", country='" + country + '\'' +
				 '}';
	}
}
