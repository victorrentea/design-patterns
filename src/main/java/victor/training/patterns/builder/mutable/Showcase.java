package victor.training.patterns.builder.mutable;

import java.util.List;

import static java.util.Arrays.asList;

public class Showcase {
	public static void main(String[] args) {
		// before

		Customer customer = new Customer()
						.setName("John Doe")
						.setLabels(asList("Label1"))
						.setAddress(new Address()
							.setStreetName("La Rambla")
							.setCity("Barcelona")
							.setCountry("Spain"));





		//after
		Customer customerFluent = new Customer()
				.setName("John Doe")
				.setLabels(List.of("Label1"))
				.setAddress(new Address()
						.setStreetName("La Rambla")
						.setCity("Barcelona")
						.setCountry("Spain"));

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
