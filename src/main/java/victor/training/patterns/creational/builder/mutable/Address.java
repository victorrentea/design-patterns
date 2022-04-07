package victor.training.patterns.creational.builder.mutable;


import lombok.Data;

@Data
public class Address {
	private String streetName;
	private String city;
	private String country;

	@Override
	public String toString() {
		return "Address{" +
				 "streetName='" + streetName + '\'' +
				 ", city='" + city + '\'' +
				 ", country='" + country + '\'' +
				 '}';
	}
}
