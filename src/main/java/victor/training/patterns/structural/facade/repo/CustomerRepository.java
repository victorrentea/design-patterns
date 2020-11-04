package victor.training.patterns.structural.facade.repo;

import org.springframework.stereotype.Repository;
import victor.training.patterns.structural.facade.entity.Customer;

@Repository
public class CustomerRepository {
	public Customer getCustomerByEmail(String email) {
		return null; // TODO
	}

	public boolean customerExistsWithEmail(String email) {
		return false; // TODO
	}

	public void save(Customer customer) {
		// TODO
	}

	public Customer findById(long customerId) {
		return null; // TODO
	}
}
