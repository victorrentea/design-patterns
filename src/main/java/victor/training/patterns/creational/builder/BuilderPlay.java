package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.List;

public class BuilderPlay {

	public static void main(String[] args) {

		
		Customer customer = new CustomerBuilder()
				.withName("John Doe")
				.addLabel("Label1")
				.withAddress(new AddressBuilder()
						.withCity("Limassol")
						.withStreetName("Dristor"))
				.build();
		
		
		

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetAddress());
	}
}
