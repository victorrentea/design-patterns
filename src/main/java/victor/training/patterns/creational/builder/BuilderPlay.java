package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {

		Customer customer = new Customer("John Doe")
			.addLabels("Label1");

//		List<String> labels = new ArrayList<>();
//		labels.add("Label1");
//		customer.setLabels(labels);

		new Customer("null").setName(null);

		Address address = new Address();
		address.setStreetName("Viorele");
		address.setCity("Bucharest");
		customer.setAddress(address);


		ImmutableObj build = ImmutableObj.builder().a("a").b("b").build();


//		new CustomerBuilder()
//			.withName("John Doe")
//				.withAddress()

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
