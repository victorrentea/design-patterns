package victor.training.oo.structural.facade.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.SiteRepository;

import java.text.SimpleDateFormat;

@Component
public class CustomerMapper {
   @Value("${simple.date.pattern}")
   private String datePattern;
   @Autowired
   private SiteRepository siteRepo;

   public CustomerDto convertToDto(Customer customer) {
      CustomerDto dto = new CustomerDto();
      dto.name = customer.getName();
      dto.email = customer.getEmail();
      dto.creationDateStr = new SimpleDateFormat(datePattern).format(customer.getCreationDate());
      dto.id = customer.getId();
      return dto;
   }

   public Customer convertToEntity(CustomerDto dto) {
      Customer customer = new Customer();
      customer.setEmail(dto.email);
      customer.setName(dto.name);
      customer.setSite(siteRepo.getReference(dto.countryId));
      return customer;
   }
}
