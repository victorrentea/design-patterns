package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

//		Address address = new AddressBuilder()
//				.withStreetName("Viorele")
//				.withCity("Bucharest")
//				.build();
//		Customer customer = new CustomerBuilder()
//				.withName("John Doe")
//				.withLabel("Label1", "Label2")
//				.withAddress(address)
//				.build();

		Customer customer = new Customer()
				.setName("John Doe")
				.addLabels("Label1", "Label2")
				.setAddress(new Address()
					.setStreetName("Viorele")
					.setCity("Bucharest"));

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
