package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

//		Customer customer = new CustomerBuilder()
//			.withName("John")
//			.addLabel("Label1", "Label2")
//			.withAddress(new AddressBuilder()
//				.withStreetName("Viorele")
//				.withCity("Bucharest")
//				.build())
//			.build();

//		statmentFromXml
//			.setParameter("name", "smth")
//			.setParameter("phone", "+13921898198");

		Customer customer = new Customer("John")
			.setName(null)
			.setAddress(new Address()
				.setStreetName("Viorele")
				.setCity("Bucharest"));

		bizLogic(customer);
	}

	private static void bizLogic(Customer customer) {
		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
