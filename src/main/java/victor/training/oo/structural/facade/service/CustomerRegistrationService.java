package victor.training.oo.structural.facade.service;

import org.springframework.stereotype.Service;

import victor.training.oo.structural.facade.entity.Customer;

@Service
public class CustomerRegistrationService {
	public void registerCustomer(Customer customer) {
		System.out.println("Registering customer "+customer.getName());
	}
}
