package victor.training.patterns.facade.facade;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.entity.Email;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.infra.EmailClient;
import victor.training.patterns.facade.repo.CustomerRepo;
import victor.training.patterns.facade.repo.EmailRepo;
import victor.training.patterns.facade.repo.SiteRepo;
import victor.training.patterns.facade.service.RegisterCustomerService;

import javax.annotation.PostConstruct;

//@RequiredArgsConstructor
//class CustomerService {
//	private final CustomerRepo customerRepo;
//
//	public Customer findById(long customerId) {
//		// "indirection without abstraction" = just going through this method without HIDING andything.
//		return customerRepo.findById(customerId); // "Middle Man" code smell
//	}
//}
//@Facade
@RestController // # instance = 1
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final RegisterCustomerService registerCustomerService;
	private String currentUsername;

	public CustomerFacade(CustomerRepo customerRepo, EmailClient emailClient, EmailRepo emailRepo, SiteRepo siteRepo, RegisterCustomerService registerCustomerService) {
		this.customerRepo = customerRepo;
		this.emailClient = emailClient;
		this.emailRepo = emailRepo;
		this.siteRepo = siteRepo;
		this.registerCustomerService = registerCustomerService;
	}


	@GetMapping("{customerId}")
	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		currentUsername = "john for this request";
		// push this to?
		// - hand-crafted or auto-mapper
		// - NOT service => propagate the API model inside my core logic.
		// - dto = new CustomerDto(customer); // if you have CONTROL on your DTO (ie you didnt generated them)
		// - NOT dto = customer.toDto(); // push the conversion in the @Entity  BAD BAD BAD BAD you pollute the Domain with Presentation/API concerns
		// - KT: fun Customer.toDto() { } extgension functions ❤️
		return new CustomerDto(customer);
	}

//	@EventListener
//	@Scheduled
//	@PostConstruct
	@PostMapping
	public void register(CustomerDto dto) {
		currentUsername = "john for this request";
		// see above solutions
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));
		System.out.println(currentUsername);
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
