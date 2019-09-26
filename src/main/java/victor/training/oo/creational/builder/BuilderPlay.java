package victor.training.oo.creational.builder;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {
		
		Customer customer = new CustomerBuilder()
				.withName("John Doe")
				.addLabel("Label1")
				.withAddress(new AddressBuilder()
						.withCity("Bucharest")
						.withStreetName("Viorele")
						.build())
				.build();
		
		
		
		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
		
		// 2
		String s = new StringBuilder()
			.toString();
	}
}
