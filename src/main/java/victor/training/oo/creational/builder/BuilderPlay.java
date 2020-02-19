package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuilderPlay {

    public static void main(String[] args) {

        Customer customer = new Customer()
                .setName("John Doe") //
                .setLabels(Arrays.asList("Label1")) //
                .setAddress(new Address()
                        .setStreetName("Viorele")
                        .setStreetNumber(4)
                        .setCity("Bucharest"));

        System.out.println("Customer name: " + customer.getName());
        System.out.println("Customer address: " + customer.getAddress().getStreetName());

        // 2
        String s = new StringBuilder()
                .toString();
    }
}
