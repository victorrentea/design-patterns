package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.List;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setName("John Doe");
		List<String> labels = new ArrayList<>();
		labels.add("Label1");
		customer.setLabels(labels);
		Address address = new Address();
		address.setStreetName("Viorele");
		address.setCity("Bucharest");
		customer.setAddress(address);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
