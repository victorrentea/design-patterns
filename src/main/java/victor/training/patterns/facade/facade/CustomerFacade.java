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

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final CustomerMapper customerMapper;
	private final RegisterCustomerService  registerCustomerService;

	public CustomerDto findById(long customerId) {
		return customerMapper.toDto(customerRepo.findById(customerId));
	}

	public void register(CustomerDto dto) {
		Customer customer = customerMapper.toEntity(dto);
		validate(customer);
		registerCustomerService.registerCustomer(customer);
		sendRegistrationEmail(customer.getEmail());
	}

	private void validate(Customer customer) {
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}
		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
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
