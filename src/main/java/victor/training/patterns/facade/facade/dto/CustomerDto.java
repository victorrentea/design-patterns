package victor.training.patterns.facade.facade.dto;

import victor.training.patterns.facade.entity.Customer;

import javax.validation.constraints.*;
import java.text.SimpleDateFormat;

public class CustomerDto {
   public Long id;
   @Size(min = 3)
   @NotNull
   public String name;
   @Email
//   @Pattern(reg)
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

}
