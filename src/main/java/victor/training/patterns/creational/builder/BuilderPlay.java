package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import static java.util.Arrays.asList;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setLabels(asList("Label1"));
		Address address = new Address();
		address.setStreetName("Viorele");
		address.setCity("Bucharest");
		customer.setAddress(address);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
