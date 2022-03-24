package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.Arrays;

public class BuilderPlay {

   public static void main(String[] args) {

      // 1 chained setters
      // 3 build easily test data
      // 4 guide a fluent API

      // 2 avoid a heavy constructor < for immutable objects. > goal=no setters.

      Customer customer = new Customer("John Doe", new Address()
          .setStreetName("Viorele")
          .setCity("Bucharest"))
//			.withAddress(newAddress).withPhone(newPhone).
          // if they change together, they stick together.
//			.withContactDetails(new ContactDetails(newPHone,newAddress))
          .withLabels(Arrays.asList("Label1"));


//		new Builder(testData)
//			.withX(different)
//			.build();
//		testData.withX(different);

//		Customer customer = 	new Customer.Builder()
//			.withName("John Doe")
//			.withLabels(Arrays.asList("Label1"))
//			.withAddress(new Address()
//				.setStreetName("Viorele")
//				.setCity("Bucharest"))
//			.build();


      System.out.println("Customer name: " + customer.getName());
      System.out.println("Customer address: " + customer.getAddress().getStreetName());
   }
}
