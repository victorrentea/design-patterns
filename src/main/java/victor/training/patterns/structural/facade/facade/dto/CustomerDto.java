package victor.training.patterns.structural.facade.facade.dto;

import lombok.Data;
import victor.training.patterns.structural.facade.entity.Customer;

@Data
public class CustomerDto {
   public Long id;
   //   @NotNull
//   @Min(6)
//@CustomerName
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;

   public CustomerDto() {
   }

   public CustomerDto(Customer customer) {
//      name = customer.getName();
//      email = customer.getEmail();
//      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
//      id = customer.getId();
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

}
