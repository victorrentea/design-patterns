package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withLabels("Label1", "Label2")
			.withAddress(new AddressBuilder()
				.withStreetName("Viorele")
				.withCity("Bucharest"))
			.build();

//		customer = new Customer(1L, "John Doe", new Date())
//			.withLabels(Arrays.asList("Label1"))
//			.withAddress(new AddressBuilder()
//				.withStreetName("Viorele")
//				.withCity("Bucharest"));

//		customer = new Customer()
//			.setName("John Doe")
//			.setLabels(Arrays.asList("Label1"))
//			.setAddress(new Address()
//				.setStreetName("Viorele")
//				.setCity("Bucharest"));


		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
