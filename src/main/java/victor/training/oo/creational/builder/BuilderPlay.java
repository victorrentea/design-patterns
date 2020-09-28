package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withLabels("Label1")
			.withAddress(new AddressBuilder()
				.withCity("Chisinau")
				.withStreetName("Stefan cel Mare"))
//			.persist()
//			.withInvalidAddress()
			.build();



		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
