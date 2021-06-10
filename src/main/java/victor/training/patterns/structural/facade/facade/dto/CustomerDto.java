package victor.training.patterns.structural.facade.facade.dto;

import org.hibernate.validator.constraints.Length;
import victor.training.patterns.structural.facade.entity.Customer;

import java.text.SimpleDateFormat;

public class CustomerDto {
   public Long id;
   @Length(min = 2)
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;

   public CustomerDto(Customer customer) {
      name = customer.getName();
      email = customer.getEmail();
      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

   public Customer toEntity() {
      Customer customer = new Customer();
      customer.setEmail(email);
      customer.setName(name);
      return customer;
   }
}
