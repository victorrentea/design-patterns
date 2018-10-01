package victor.training.oo.structural.facade.service;

import org.springframework.stereotype.Service;

import victor.training.oo.structural.facade.entity.Customer;

@Service
public class EmailService {
	public void sendActivationEmail(Customer customer) {
		System.out.println("Sending activation link via email to "+ customer.getEmail());
	}
}
