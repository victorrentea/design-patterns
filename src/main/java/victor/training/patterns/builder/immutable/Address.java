package victor.training.patterns.builder.immutable;

public class Address {
	private final String streetName;
	private final String streetNumber;
	private final String city;
	private final String aptNumber; // optional field

	public Address(String streetName, String streetNumber, String city, String aptNumber) {
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.aptNumber = aptNumber;
	}

	// getters ...

	@Override
	public String toString() {
		return "Address{" +
				 "streetName='" + streetName + '\'' +
				 ", streetNumber=" + streetNumber +
				 ", city='" + city + '\'' +
				 ", aptNumber='" + aptNumber + '\'' +
				 '}';
	}
}
