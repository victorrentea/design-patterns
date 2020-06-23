package victor.training.oo.creational.builder;

//import java.util.ArrayList; // INITIAL
//import java.util.List; // INITIAL

import java.util.Arrays;

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


		Customer customer2 = new Customer()
			.setName("John Doe")
			.setAddress(new Address()
				.setStreetName("Viorele"))
			.setLabels(Arrays.asList("l1", "l2"));
//			.setOrders(Arrays.asList(new Order()));
//		customer2.getOrders().add(new Order()); // ex la runtime - e riscant. ca e posibil

		for (Order order : customer2.getOrdersIterable()) {

		}

		Order order = new Order();
		customer2.getOrdersGeneric().contains(order);
//		customer2.getOrdersGeneric().add(order); // nu compileaza -> e mai devreme crapelinitza, si ai si metodele colectiei disponibile
		for (Order order1 : customer2.getOrdersGeneric()) {

		}

		customer2.addOrder(new Order());

		Order orderHack = new Order();
		orderHack.setCustomer(customer2);

		System.out.println("Customer name: " + customer.getName());
		System.out.println("Customer address: " + customer.getAddress().getStreetName());
	}
}
