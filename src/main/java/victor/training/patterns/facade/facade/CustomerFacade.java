package victor.training.patterns.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.Facade;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.entity.Email;
import victor.training.patterns.facade.infra.EmailClient;
import victor.training.patterns.facade.repo.CustomerRepo;
import victor.training.patterns.facade.repo.EmailRepo;
import victor.training.patterns.facade.repo.SiteRepo;
import victor.training.patterns.facade.service.CustomerService;
import victor.training.patterns.facade.service.NotificationService;

import java.text.SimpleDateFormat;

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

		customerService.registerCustomer(customer);

		notificationService.sendRegistrationEmail(customer.getEmail());
	}


	private final CustomerService customerService;
}
