package victor.training.patterns.builder.small;

import org.checkerframework.checker.units.qual.A;

import static java.util.Objects.requireNonNull;

public class Address {
	private final String streetName;
	private final String streetNumber;
	private final String city;
	private final String aptNumber; // optional field
	private final String floorNumber; // optional field

	// canonical constructor (taking all fields, all other ctors delegate to this)
	public Address(String streetName, String streetNumber, String city, String aptNumber, String floorNumber) {
		this.streetName = requireNonNull(streetName);
		this.streetNumber = requireNonNull(streetNumber);
		this.city = requireNonNull(city);
		this.aptNumber = aptNumber;
		this.floorNumber = floorNumber;
	}

	// minimal constructor (taking only the required fields)
	public Address(String streetName, String streetNumber, String city) {
		this(streetName, streetNumber, city, null, null);
	}

	// withers ... (derive a changed copy)
	public Address withAptNumber(String aptNumber) {
		return new Address(streetName, streetNumber, city, aptNumber, floorNumber);
	}
	public Address withFloorNumber(String floorNumber) {
		return new Address(streetName, streetNumber, city, aptNumber, floorNumber);
	}

	// getters ...
	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getAptNumber() {
		return aptNumber;
	}

	public String getCity() {
		return city;
	}

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
