package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.List;

public class BuilderPlay {

	public static void main(String[] args) {

		
		Customer customer = TestData.aValidCustomer();
		
		

		System.out.println("Customer name: " + customer.getFullName());
		System.out.println("Customer address: " + customer.getAddress().getStreetAddress());
	}
}