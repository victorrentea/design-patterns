package victor.training.patterns.structural.facade.facade.dto;

import victor.training.patterns.structural.facade.entity.Customer;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;

public class CustomerDto {
   public Long id;
   @Size(min = 5)
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


   // i dispise Dtos
   public CustomerDto(Customer customer) { // if you don't package Dto in Jar and if you DON't generate Dtos from yaml/json
      name = customer.getName();
      email = customer.getEmail();
      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }

   public Customer toEntity() {
      Customer customer = new Customer();
      customer.setEmail(email);
      customer.setName(name);
      return customer;
   }
//   public CustomerDto(CustomerEntinty2 customer) {
//      name = customer.getName();
//      email = customer.getEmail();
//      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
//      id = customer.getId();
//   }
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