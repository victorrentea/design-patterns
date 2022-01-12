package victor.training.patterns.structural.facade.facade;

import org.springframework.beans.factory.annotation.Autowired;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.repo.EmailRepo;
import victor.training.patterns.structural.facade.service.RegisterCustomerService;

@Facade
public class CustomerFacade {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private EmailClient emailClient;
	@Autowired
	private EmailRepo emailRepo;
	@Autowired
	RegisterCustomerService customerDomainService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}

	public void register(CustomerDto dto) {
		Customer customer = dto.toEntity();

		//3 linii merg si aici
		validate(customer);

		customerDomainService.register(customer);

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
