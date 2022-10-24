package victor.training.patterns.builder.mutable;

import static java.util.Arrays.asList;

public class BuilderPlay {

	public static void main(String[] args) {

		// fragmented, hard to read:

		Address address = new Address();
		address.setStreetName("La Rambla");
		address.setCity("Barcelona");
		address.setCountry("Spain");
		Customer customer = new Customer()
				.setName("John Doe")
				.setLabels(asList("Label1"))
				.setAddress(address); // in tests = ❤️  ~ kt .copy()

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
