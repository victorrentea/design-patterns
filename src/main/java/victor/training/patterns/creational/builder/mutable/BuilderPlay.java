package victor.training.patterns.creational.builder.mutable;

import static java.util.Arrays.asList;

public class BuilderPlay {

	private Customer customer = new Customer();

	public static void main(String[] args) {


		// fragmented, hard to read:

		Customer customer = aCustomer().build();
		Customer customerFaraNume = aCustomer().withName(null).build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}

	private static CustomerBuilder aCustomer() {
		return new CustomerBuilder()
			.withName("John Doe")
			.withLabels(asList("Label1"))
			.withAddress(new AddressBuilder()
				.withStreetName("La Rambla")
				.withCity("Barcelona")
				.withCountry("Spain")
				.build());
	}
}
