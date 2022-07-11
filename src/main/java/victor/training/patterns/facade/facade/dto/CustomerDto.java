package victor.training.patterns.facade.facade.dto;

import lombok.Data;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.facade.CustomerValidator;

import java.text.SimpleDateFormat;

public class CustomerDto {
   public Long id;
//   @NotNull
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;

   public static CustomerDto fromEntity(Customer customer) { // overengineering.
      // metodele factory statice sunt folosite
      // cand vrei sa ascunzi ceva (polimorfism, caching, singleton)
      CustomerDto dto = new CustomerDto();
      dto.name = customer.getName();
      dto.email = customer.getEmail();
      dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      dto.id = customer.getId();
      return dto;
   }
   
   public CustomerDto(Customer customer) { // e ok pentru ca DTO e un ob neimportant periferic care ARE voie sa depindaa de entitat.
      // !! 
      name = customer.getName();
      email = customer.getEmail();
      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }

   public CustomerDto() {} // pt jackson cand face unmarhsall din JSON

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

}
