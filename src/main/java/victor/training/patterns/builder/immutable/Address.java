package victor.training.patterns.builder.immutable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Builder
@Value
@AllArgsConstructor
public class Address {
	String streetName;
	String streetNumber;
	String city;
	String aptNumber; // optional field
	@With
	String floorNumber;
	String floorNumber1;
	String floorNumber2;
	String floorNumber3;
	String floorNumber4;

//	public Address() {}

}
