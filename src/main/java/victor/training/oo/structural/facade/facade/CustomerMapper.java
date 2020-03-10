package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.SiteRepository;

@Service
@RequiredArgsConstructor
public class CustomerMapper {
    private final SiteRepository siteRepo;

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.email);
        customer.setName(dto.name);
        customer.setSite(siteRepo.getReference(dto.countryId));
        return customer;
    }
}
