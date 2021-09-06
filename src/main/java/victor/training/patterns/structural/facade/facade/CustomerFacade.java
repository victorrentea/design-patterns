package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.mapper.CustomerMapper;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.repo.EmailRepo;
import victor.training.patterns.structural.facade.repo.SiteRepo;
import victor.training.patterns.structural.facade.service.CustomerService;

import javax.xml.validation.Validator;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final CustomerMapper customerMapper;
	private final CustomerService customerService;
	@Autowired
	Validator validator;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return customerMapper.toDto(customer);
	}

	public void register(CustomerDto dto) {
		Customer customer = customerMapper.toEntity(dto);
//		validator.validate(customer);

		if (customer.getName().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

//		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
//			throw new IllegalArgumentException("Email already registered");
//		}

		customerService.registerCustomer(customer);

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
