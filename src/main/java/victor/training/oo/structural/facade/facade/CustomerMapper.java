package victor.training.oo.structural.facade.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;

import java.text.SimpleDateFormat;

@Component
public class CustomerMapper {

   private SimpleDateFormat simpleDateFormat;

   public void setDatePattern(@Value("${simple.date.pattern}") String datePattern) {
      simpleDateFormat = new SimpleDateFormat(datePattern);
   }

   public CustomerDto convertToDto(Customer customer) {
      CustomerDto dto = new CustomerDto();
      dto.name = customer.getName();
      dto.email = customer.getEmail();
      dto.creationDateStr = simpleDateFormat.format(customer.getCreationDate());
      dto.id = customer.getId();
      return dto;
   }

}
