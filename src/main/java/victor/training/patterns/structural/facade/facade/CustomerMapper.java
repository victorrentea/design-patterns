package victor.training.patterns.structural.facade.facade;

import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;

import java.text.SimpleDateFormat;

public class CustomerMapper {
   public CustomerDto toDto(Customer customer) {
      CustomerDto dto = new CustomerDto();
      dto.name = customer.getName();
      dto.email = customer.getEmail();
      dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      dto.id = customer.getId();
      return dto;
   }
}