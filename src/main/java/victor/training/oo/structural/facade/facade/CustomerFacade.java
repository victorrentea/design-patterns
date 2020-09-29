package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.service.EmailService;
import victor.training.oo.structural.facade.service.RegisterCustomerService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepository customerRepo;
	private final EmailService emailService;
	private final CustomerMapper customerMapper;
	private final RegisterCustomerService customerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return customerMapper.convertToDto(customer);
	}


	public void registerCustomer(CustomerDto dto) {
		Customer customer = customerMapper.convertToEntity(dto);
		validateCustomer(customer);
		customerService.registerCustomer(customer);
		emailService.sendRegistrationEmail(customer.getEmail());
	}


	private void validateCustomer(Customer customer) {
		//TODO extract to CustomerValidator daca mai creste
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
	}


}
