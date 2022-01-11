package victor.training.patterns.creational.factory;

import victor.training.patterns.structural.facade.entity.Customer;

import java.time.LocalDate;

public class TestData { // Object Mother doar pui

   public static Customer aCustomer() { // 255 de teste folosesc metoda asta
      Customer customer = new Customer();
      customer.setEmail("a@e.com");
      customer.setName("John Doe");
      customer.setGoldMember(true);
      customer.setCreationDate(LocalDate.now());
      return customer;
   }
}
