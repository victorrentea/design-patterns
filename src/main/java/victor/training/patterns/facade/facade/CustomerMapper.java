package victor.training.patterns.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.entity.Site;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.repo.SiteRepo;

import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Component
public class CustomerMapper {
    private final SiteRepo siteRepo;
//CustomerDocument
    public Customer toEntity(CustomerDto dto) {
        Site site = siteRepo.getReference(dto.countryId);
        Customer customer = new Customer();
        customer.setEmail(dto.email);
        customer.setName(dto.name);
        customer.setSite(site);
        return customer;
        // where else can i convert a incoming DTO into an entity of my
        // - in the controller, BUT that will make that big!
        // - NEVER in the entity
        // - mapStruct/modelmapper/dozer: CAREFUL on the long run
        // - in Dto itself IF you have control on those clases (not in picnic)
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
