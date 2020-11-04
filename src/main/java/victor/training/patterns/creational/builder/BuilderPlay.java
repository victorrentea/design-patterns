package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.List;

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer()
			.setName("John Doe")
			.setAddress(new Address()
				.setCity("Bucharest")
				.setStreetAddress("Viorele 4")
			)
			.addLabel("Label1", "Label2");

			;
//		List<String> labels = new ArrayList<>();
//		labels.add("Label1");
//		labels.add("Label2");
//		customer.setLabels(labels);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetAddress());
	}
}
