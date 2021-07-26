package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL


import java.util.Arrays;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer()
			.setName("John Doe")
			.setAddress(new Address()
				.setStreetName("Viorele")
				.setCity("Bucharest"))
			.setLabels(Arrays.asList("Label1"));

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
