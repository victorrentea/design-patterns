package victor.training.patterns.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

public class BuilderPlay {

	public static void main(String[] args) {


		Customer customer = new Customer()
			.setName("John Doe")
			.addLabels("Label1", "Label2")
			.setAddress(new Address()
				.setCity("Bucuresti")
				.setStreetName("Viorele"));

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
