package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.service.CustomerRegistrationService;
import victor.training.oo.structural.facade.service.EmailService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepository customerRepo;
	private final CustomerRegistrationService registrationService;
	private final EmailService emailService;

	public void registerCustomer(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		
		if (customerRepo.getCustomerByEmail(customer.getEmail()) == null) {
			registrationService.registerCustomer(customer);
			emailService.sendActivationEmail(customer); // Extragi ca e SRP cica
		} else {
			throw new IllegalArgumentException("Email already registered");
		}
	}
}
