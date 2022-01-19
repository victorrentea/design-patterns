package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {
//		Customer customer = new Customer();
//		customer.setName("John Doe");
//		Address address = new Address();
//		address.setStreetName("Viorele");
//		address.setCity("Bucharest");
//		customer.setAddress(address);

		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withAddress(new AddressBuilder()
				.withStreetName("Iuliu Maniu")
				.withCity("Bucuresti, Regie")
				.build())
			.build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
