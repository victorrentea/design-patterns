package victor.training.patterns.behavioral.observer;

import org.junit.Test;
import victor.training.patterns.creational.builder.Address;
import victor.training.patterns.creational.builder.Customer;

import java.util.Date;

import static victor.training.patterns.behavioral.observer.TestData.aValidCustomer;

public class SomeTest {
   @Test
   public void test() {

      Customer customer = aValidCustomer()
          .setAddress(new Address());
   }

   @Test
   public void test2() {

      Customer customer = aValidCustomer()
          .setAddress(null);
   }

}

class TestData {
   public static Customer aValidCustomer() {
      return new Customer()
          .setName("John")
          .setCreateDate(new Date());
   }

}
