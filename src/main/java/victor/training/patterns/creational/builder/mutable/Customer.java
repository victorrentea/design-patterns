package victor.training.patterns.creational.builder.mutable;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<String> labels = new ArrayList<>();
	private Address address;

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getLabels() {
		return labels;
	}

	public Customer setLabels(List<String> labels) {
		this.labels = labels;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Customer setAddress(Address address) {
		this.address = address;
		return this;
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
