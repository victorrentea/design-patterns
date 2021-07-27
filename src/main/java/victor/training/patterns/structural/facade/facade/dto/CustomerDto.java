package victor.training.patterns.structural.facade.facade.dto;

import victor.training.patterns.structural.facade.entity.Customer;

import java.text.SimpleDateFormat;

public class CustomerDto { // = GUNOI (pt BE)
   public Long id;
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;

   public CustomerDto() {
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

   public CustomerDto(Customer customer) {
      name = customer.getName();
      email = customer.getEmail();
      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }

}
