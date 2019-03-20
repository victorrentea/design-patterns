package victor.training.oo.creational.builder;

import org.apache.commons.lang.StringUtils;

public class CustomerValidator {
	
	public static class CustomerWithoutNameException extends IllegalArgumentException {
		
	}

	public void validate(Customer customer) {
		if (StringUtils.isBlank(customer.getName())) {
			throw new CustomerWithoutNameException();
		}
		validateAddress(customer.getAddress());
		//etc
	}
	
	private void validateAddress(Address address) {
		if (address == null) {
			throw new IllegalArgumentException("Missing customer address");
		}
		if (StringUtils.isBlank(address.getCity())) {
			throw new IllegalArgumentException("Missing address xcity");
//			throw new MyOnlyOneException(ErrorCode.CUSTOMER_WITHOUT_CITY);
		}
	}
}
