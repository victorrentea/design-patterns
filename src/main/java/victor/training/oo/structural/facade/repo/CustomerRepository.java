package victor.training.oo.structural.facade.repo;

import org.springframework.stereotype.Repository;

import victor.training.oo.structural.facade.entity.Customer;

@Repository
public class CustomerRepository {
	public Customer getCustomerByEmail(String email) {
		System.out.println("Lookup existing customer by email (natural key)");
		return null; // let's say it doesn't find it
	}
}
