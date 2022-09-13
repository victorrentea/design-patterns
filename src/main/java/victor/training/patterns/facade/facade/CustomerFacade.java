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
import victor.training.patterns.facade.service.RegisterCustomerService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;

	public CustomerDto findById(long customerId) {
		Customer customerEntity = customerRepo.findById(customerId);
		CustomerDto dto = new CustomerDto(customerEntity);
//		CustomerDto dto = customerEntity.toDto();
		return dto;
	}

	public void register(CustomerDto dto) {
		Customer customer = new Customer(dto.name);
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));

		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		registerCustomerService.register(customer);

		sendRegistrationEmail(customer.getEmail());
	}
	private final RegisterCustomerService registerCustomerService;

	private void sendRegistrationEmail(String emailAddress) {
		System.out.println("Sending activation link via email to " + emailAddress);
		Email email = new Email();
		email.setFrom("noreply");
		email.setTo(emailAddress);
		email.setSubject("Welcome!");
		email.setBody("You'll like it! Sincerely, Team");

		if (!emailRepo.emailWasSentBefore(email.hashCode())) {
			emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
			emailRepo.saveSentEmail(email);
		}
	}
}
