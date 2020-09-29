package victor.training.oo.structural.facade.facade;

import org.springframework.stereotype.Component;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;

@Component
public class CustomerMapper {
   public CustomerDto convertToDto(Customer customer) {
      CustomerDto dto = new CustomerDto(customer);
//      dto.name = customer.getName();
//      dto.email = customer.getEmail();
//      dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
//      dto.id = customer.getId();
      return dto;
   }

}
