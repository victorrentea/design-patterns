package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.mapper.CustomerMapper;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.service.NotificationService;
import victor.training.patterns.structural.facade.service.RegisterCustomerService;

import javax.xml.validation.Validator;

@Facade
@RequiredArgsConstructor
public class CustomerFacade { // orchestrator
	private final CustomerRepo customerRepo;

	private final CustomerMapper customerMapper;
	private final RegisterCustomerService registerCustomerService;
	private final NotificationService notificationService;

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

		registerCustomerService.registerCustomer(customer);

		notificationService.sendRegistrationEmail(customer.getEmail());
	}

}


// 200-250 LOC