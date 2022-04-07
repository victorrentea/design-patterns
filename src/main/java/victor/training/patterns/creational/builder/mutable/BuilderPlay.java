package victor.training.patterns.creational.builder.mutable;

import static java.util.Arrays.asList;

public class BuilderPlay {

	private Customer customer = new Customer();

	public static void main(String[] args) {


		Customer customer1 = aCustomerFaraBuilder();
		Customer customer2 = aCustomerFaraBuilder();

		// fragmented, hard to read:

		Customer customer = aCustomer().build();
		Customer customerFaraNume = aCustomer().withName(null).build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}

	private static Customer aCustomerFaraBuilder() {
		return new Customer()
			.setName("John Doe")
			.setLabels(asList("Label1"))
			.setAddress(new Address()
				.setStreetName("Viorele")
				.setCity("Bucharest")
				.setStreetName("La Rambla")
				.setCity("Barcelona")
				.setCountry("Spain"));
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
