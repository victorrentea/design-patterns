package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BuilderPlay {

   public static void main(String[] args) {


      List<String> list = new ArrayList<>();


      Customer customer = new CustomerBuilder()
          .withName("John Doe")
          .addLabel("Label1", "Label2")
          .setAddress(new AddressBuilder()
              .withStreetName("Viorele")
              .withCity("Bucharesti")
              .build())
          .build();


      System.out.println("Customer name: " + customer.getName());
      System.out.println("Customer address: " + customer.getAddress().getStreetAddress());
   }
}
