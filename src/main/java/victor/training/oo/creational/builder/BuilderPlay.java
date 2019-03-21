package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {
		
		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.withLabel("Label1")
			.withAddress(new AddressBuilder()
				.withStreetName("Viorele")
				.withStreetNumber(4)
				.withCity("Bucharest")
				.build())
			.build();
		
		
		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
		
		// 2
		String s = new StringBuilder()
			.toString();
	}
}
