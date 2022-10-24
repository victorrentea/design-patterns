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
import victor.training.patterns.facade.service.CustomerService;

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
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final CustomerService customerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		// push this to?
		// - hand-crafted or auto-mapper
		// - NOT service => propagate the API model inside my core logic.
		// - dto = new CustomerDto(customer); // if you have CONTROL on your DTO (ie you didnt generated them)
		// - NOT dto = customer.toDto(); // push the conversion in the @Entity  BAD BAD BAD BAD you pollute the Domain with Presentation/API concerns
		// - KT: fun Customer.toDto() { } extgension functions ❤️
		return new CustomerDto(customer);
	}

	public void register(CustomerDto dto) {
		// see above solutions
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));

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
