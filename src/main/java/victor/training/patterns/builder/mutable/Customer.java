package victor.training.patterns.builder.mutable;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<String> labels = new ArrayList<>();
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "Customer{" +
				 "name='" + name + '\'' +
				 ", labels=" + labels +
				 ", address=" + address +
				 '}';
	}
}
