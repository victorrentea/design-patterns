package victor.training.patterns.structural.facade.facade.dto;

import victor.training.patterns.structural.facade.entity.Customer;

public class CustomerDto {
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

}

class CustomerMapper {
   public CustomerDto toDto(Customer customer) {
      CustomerDto customerDto = new CustomerDto();
      customer.setEmail(customer.getEmail());
      customer.setCreationDate(customer.getCreationDate());
      customer.setName(customer.getName());
//      customer.setCreationDate(customer.getCreationDate().format(DateTimeFormatter.ofPattern("")))
      return customerDto;

   }
}
// AutoMapper, Dozer, MapStruct