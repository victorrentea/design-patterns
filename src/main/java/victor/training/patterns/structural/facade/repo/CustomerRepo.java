package victor.training.patterns.structural.facade.repo;

import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import victor.training.patterns.structural.facade.entity.Customer;

import javax.validation.Valid;

@Repository
@Validated
public class CustomerRepo {
	public Customer getCustomerByEmail(String email) {
		return null; // TODO
	}

	public boolean customerExistsWithEmail(String email) {
		return false; // TODO
	}

	public void save(@Valid Customer customer) {
		// TODO
	}

	public Customer findById(long customerId) {
		return null; // TODO
	}
}
