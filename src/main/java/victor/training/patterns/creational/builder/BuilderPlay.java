package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import static java.util.Arrays.asList;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setLabels(asList("Label1"));

		// builders for mutable structures are useless : fluent setters

		Address address =
			new Address("Viorele", "Bucharest", "Romania")
				.withStreetNumber(4); // wither

		// Builders can make the creation of immutable objects easier to read and less prone to confusion prone
		// when the constructor of that immutable object has many parameters (5+)
		address = new AddressBuilder()
			.withCity("Bucharest")
			.withStreetName("Viorele")
			.withCountry("Romania")
			.build();

		customer.setAddress(address);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
