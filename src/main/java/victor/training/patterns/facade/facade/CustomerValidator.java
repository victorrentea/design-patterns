package victor.training.patterns.facade.facade;

import org.springframework.stereotype.Component;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;

@Component
public class CustomerValidator {
	private final CustomerRepo customerRepo;

	CustomerValidator(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	public void validate(Customer customer) {
		if (customer.getEmail() == null) {
			throw new IllegalArgumentException("AFARA");
		}
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
	}
}
