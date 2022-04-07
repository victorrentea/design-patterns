package victor.training.patterns.creational.builder.immutable;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.Objects;

@Data
@Builder
public class Address {
	//	private final StreetAddress streetAddress;
	private final String streetName;
	private final String streetNumber;
	private final String city;
	//	@With
	private final String aptNumber; // optional field

	public Address(String streetName, String streetNumber, String city) {
		this(streetName, streetNumber, city, null);
	}

	// canonic constructor
	public Address(String streetName, String streetNumber, String city, String aptNumber) {
		this.streetName = Objects.requireNonNull(streetName);
		this.streetNumber = Objects.requireNonNull(streetNumber);
		this.city = Objects.requireNonNull(city);
		this.aptNumber = aptNumber;
	}

	public Address withAptNumber(String newAptNumber) {
		return new Address(this.streetName, this.streetNumber, this.city, newAptNumber);
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
