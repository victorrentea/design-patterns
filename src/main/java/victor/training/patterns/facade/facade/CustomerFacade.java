package victor.training.patterns.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.Facade;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;
import victor.training.patterns.facade.repo.SiteRepo;
import victor.training.patterns.facade.service.RegisterCustomerService;
import victor.training.patterns.facade.service.NotificationService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final NotificationService notificationService;
	private final SiteRepo siteRepo;

	public CustomerDto findById(long customerId) {
		// entity
		Customer customer = customerRepo.findById(customerId);

		// where can I move this logic?
		// - mapper (hand written or generated) dto = mapper.fromEntity(customer);
		// - in the Dto constructor
		//		CustomerDto dto = customer.toDto(); // with extension functions (kt)
		return new CustomerDto(customer);
	}

	public void register(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));

		registerCustomerService.registerCustomer(customer);

		notificationService.sendRegistrationEmail(customer.getEmail());
	}


	private final RegisterCustomerService registerCustomerService;
}
