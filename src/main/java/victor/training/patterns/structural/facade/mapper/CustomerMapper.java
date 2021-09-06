package victor.training.patterns.structural.facade.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.repo.SiteRepo;

import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
   private final SiteRepo siteRepo;

   public Customer toEntity(CustomerDto dto) {
      Customer customer = new Customer();
      customer.setEmail(dto.email);
      customer.setName(dto.name);

//      customer.setSiteId(new Site(dto.countryId));
//      customer.setSite(siteRepo.getReference(dto.countryId));
      return customer;
   }

   public CustomerDto toDto(Customer customer) {
      CustomerDto dto = new CustomerDto();
      dto.name = customer.getName();
      dto.email = customer.getEmail();
      dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      dto.id = customer.getId();
      return dto;
   }
}
