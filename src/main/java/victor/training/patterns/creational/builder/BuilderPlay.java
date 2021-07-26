package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import victor.training.patterns.creational.builder.Customer.CustomerBuilder;

import java.util.Arrays;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new CustomerBuilder()
			.name("John Doe")
			.address(new AddressBuilder()
				.withStreetName("Viorele")
				.withCity("Bucharest")
				.build())
			.labels(Arrays.asList("Label1"))
			.build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
