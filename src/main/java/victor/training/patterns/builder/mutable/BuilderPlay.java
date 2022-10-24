package victor.training.patterns.builder.mutable;

import static java.util.Arrays.asList;

public class BuilderPlay {

	public static void main(String[] args) {

		Address address = new Address();
		address.setStreetName("La Rambla");
		address.setCity("Barcelona");
		address.setCountry("Spain");

		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setLabels(asList("Label1"));
		customer.setAddress(address);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
