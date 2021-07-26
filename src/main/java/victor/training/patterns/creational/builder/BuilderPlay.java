package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withAddress(new AddressBuilder()
				.withStreetName("Viorele")
				.withCity("Bucharest")
				.build())
			.withLabels("Label1")
			.build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
