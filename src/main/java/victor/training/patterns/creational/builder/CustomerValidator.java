package victor.training.patterns.creational.builder;

import static org.apache.commons.lang.StringUtils.isBlank;

public class CustomerValidator {

	public void validate(Customer customer) {
		if (isBlank(customer.getName())) {
			throw new IllegalArgumentException("Missing customer name");
		}
		validateAddress(customer.getAddress());
		//etc
	}
	
	private void validateAddress(Address address) {
		if (isBlank(address.getCity())) {
			throw new IllegalArgumentException("Missing address city");
		}
		if (isBlank(address.getStreetName())) {
			throw new IllegalArgumentException("Missing address street");
		}
	}
}
