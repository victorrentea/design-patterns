package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.List;

public class BuilderPlay {

	public static void main(String[] args) {
//		Customer customer = new CustomerBuilder()
//			.withName("John Doe")
//			.addLabel("Label1", "Label1")
//			.withAddress(new AddressBuilder()
//				.withStreetName("Viorele")
//				.withCity("Bucharest"))
//			.build();

		Customer customer = TestData.aValidCustomer();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetAddress());
	}
}

// Object Mother Pattern
class TestData {

	public TestData(String s, String p, int age, int k, String s1, String p2, int age3, int k4) {
		// TODO Auto-generated method stub

	}
	

	

	public static Customer aValidCustomer() {
		return new Customer()
				.setName("John Doe")
				.addLabels("Label1", "Label1")
				.setAddress(new Address()
						.setStreetAddress("Viorele")
						.setCity("Bucharest"));
	}
	
	
	
	
	
	static {
		new TestData("a", "b", 8, 10, "a", "b", 8, 10);
//		new TestDataBuilder()
//			.withA("a")
//			.withP("b")
//			.withAge(8)
		//
//		.build();
//		;
	}
}