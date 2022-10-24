package victor.training.patterns.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.Facade;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.entity.Email;
import victor.training.patterns.facade.infra.EmailClient;
import victor.training.patterns.facade.repo.CustomerRepo;
import victor.training.patterns.facade.repo.EmailRepo;
import victor.training.patterns.facade.repo.SiteRepo;
import victor.training.patterns.facade.service.NotificationService;

import java.text.SimpleDateFormat;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final NotificationService notificationService;
	private final SiteRepo siteRepo;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		CustomerDto dto = new CustomerDto();
		dto.name = customer.getName();
		dto.email = customer.getEmail();
		dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
		dto.id = customer.getId();
		return dto;
	}

	public void register(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));

		// Heavy business logic
		// Heavy business logic
		// Heavy business logic

		int discountPercentage = 3;
		if (customer.isGoldMember()) {
			discountPercentage += 1;
		}
		System.out.println("Biz Logic with discount " + discountPercentage);
		// Heavy business logic
		// Heavy business logic
		customerRepo.save(customer);
		// Heavy business logic

		notificationService.sendRegistrationEmail(customer.getEmail());
	}


}
