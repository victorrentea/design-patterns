package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.Arrays;

public class BuilderPlay {

	public static void main(String[] args) {
		// why builders ?
		// 1 ) easy to read with mutable data ojects (setters)
		// use @Setter + lombok.config or return this from setters

		// 2 ) immutables


		Customer customer = new CustomerBuilder()
			.withName("John")
			.withLabels("Label1")
			.withAddress(new AddressBuilder()
				.withStreetName("Viorele")
				.withCity("Bucharest")
				.build())
			.build();


		customer = new Customer()
			.setName("John")
			.setLabels(Arrays.asList("Label"))
			.setAddress(new Address()
				.setStreetName("Viorele")
				.setCity("Bucharest"))
		;

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
