package victor.training.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CustomerValidatorTest {

	private CustomerValidator validator = new CustomerValidator();
	
	@Test
	public void validCustomer_ok() {
		Customer customer = new Customer();
		customer.setName("John Doe");
		Address address = new Address();
		address.setCity("Bucharest");
		address.setStreetAddress("Dristor 91");
		customer.setAddress(address);
		validator.validate(customer);
	}

	@Test
	public void test1() throws InterruptedException {
		run(1);
	}
	@Test
	public void test2() throws InterruptedException {
		run(2);
	}
	@Test
	public void test3() throws InterruptedException {
		run(3);
	}
	public void test4() throws InterruptedException {
		run(4);
	}

	private void run(int s) throws InterruptedException {
		log.debug("Start Test" + s);
		Thread.sleep(3000);
		log.debug("End Test" + s);
	}

}