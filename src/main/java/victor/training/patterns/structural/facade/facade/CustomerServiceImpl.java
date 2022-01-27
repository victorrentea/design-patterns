package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.repo.EmailRepo;
import victor.training.patterns.structural.facade.repo.SiteRepo;
import victor.training.patterns.structural.facade.service.RegisterCustomerDomainService;

import java.text.SimpleDateFormat;

@Facade
@RequiredArgsConstructor
public class CustomerServiceImpl /*implements MyInterfaceFromApiJar*/ {
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final CustomerRepo customerRepo;
	private final RegisterCustomerDomainService registerCustomerService;

	public CustomerDto findById(long customerId) {
		Customer customer = registerCustomerService.customerRepo.findById(customerId);
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

		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}

		registerCustomerService.registerCustomer(customer);

		sendRegistrationEmail(customer.getEmail());
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
