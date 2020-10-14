package victor.training.patterns.creational.builder;

// Object Mother F...
public class TestData {

	// 200 tests rely on this
	public static Customer aValidCustomer() {
		return new Customer()
				.setFullName("John Doe")
				.addLabels("Label1", "Label2") // 20 tests break
				.setAddress(new Address()
						.setCity("Limassol")
						.setStreetAddress("Dristor"));
	}
	
}