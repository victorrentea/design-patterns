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
		address.setStreetName("Viorel4");

		address.setCity("Bucharest");
		customer.setAddress(address);


		ImmutableFoobarValue vo = ImmutableFoobarValue.builder()
			.foo(2)
			.bar("")
			.build();


//		vo.
////		vo.with

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
