package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {
		
//		customer.setName("John Doe");
//		List<String> labels = new ArrayList<>();
//		labels.add("Label1");
//		customer.setLabels(labels);
		//		address.setStreetName("Viorele");
//		address.setStreetNumber(4);
//		address.setCity("Bucharest");

		Customer customer = new CustomerBuilder()
			.withName("John Doe")
			.addLabels("Label1", "Label2")
			.withAddress(new AddressBuilder()
				.withStreetName("Viorele")
				.withCity("Bucharest")
				.withStreetNumber(4))
			.build();

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
