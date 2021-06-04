package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.repo.SiteRepo;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
   private final SiteRepo siteRepo;

   public Customer convert(CustomerDto dto) {
      Customer customer = new Customer();
      customer.setEmail(dto.email);
      customer.setName(dto.name);
      customer.setSite(siteRepo.getReference(dto.countryId));
      return customer;
   }

}
