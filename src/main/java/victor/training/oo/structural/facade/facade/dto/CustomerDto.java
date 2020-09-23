package victor.training.oo.structural.facade.facade.dto;

import victor.training.oo.structural.facade.entity.Customer;

import java.text.SimpleDateFormat;

public class CustomerDto {
   public Long id;
   public String name;
   public String email;
   public Long countryId;
   public String dateDuCreation;
//    boolean canBeDeleted;

   public CustomerDto(Customer customer) {
      name = customer.getName();
      email = customer.getEmail();
      dateDuCreation = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

   public void m() {
//       cu creier
   }
}
