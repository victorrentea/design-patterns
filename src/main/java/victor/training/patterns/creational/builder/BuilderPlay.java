package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {


		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withLabels("Label1", "Label2")
			.withAddress(new AddressBuilder()
				.withCity("Bucuresti")
				.withStreetName("Viorele")
				.build())
//			.withDefaultData()
			.persist();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
