package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.repo.EmailRepo;
import victor.training.patterns.structural.facade.service.CustomerService;

//class RegisterCustomerApplicationService {
//
//}
//class FindCustomerApplicationService {
//	public CustomerDto findById(long customerId) {
//		Customer customer = customerRepo.findById(customerId);
//		return new CustomerDto(customer);
//	}
//
//
//}
@Facade
@RequiredArgsConstructor
@Transactional // when could this be bad ? > when huge no of || tx.
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;

	private final CustomerService customerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		// http request taking 2 seconds
		return new CustomerDto(customer);
	}

	public void register(CustomerDto dto) {
		Customer customer = dto.toEntity();

//		if (customer.getName().trim().length() <= 5) {
//			throw new IllegalArgumentException("Name too short");
//		}
		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}

		customerService.register(customer);

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
