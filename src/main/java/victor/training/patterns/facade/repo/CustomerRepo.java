package victor.training.patterns.facade.repo;

import org.springframework.stereotype.Repository;
import victor.training.patterns.facade.entity.Customer;

@Repository
public class CustomerRepo {
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
