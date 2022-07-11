package victor.training.patterns.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
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
	private final CustomerValidator customerValidator;
	private final RegisterCustomerService registerCustomerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId); // relaxed layered architecture
		CustomerDto dto = new CustomerDto(customer);
		// mapper. (clasa separata)
		// in Customer ENTITY NICIODATA
		// in Dto
		return dto;
	}


	public void register(CustomerDto dto) {
		Customer customer = fromDto(dto);

		customerValidator.validate(customer);

		registerCustomerService.registerCustomer(customer);

		sendRegistrationEmail(customer.getEmail());
	}

	private Customer fromDto(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));
		return customer;
	}


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
