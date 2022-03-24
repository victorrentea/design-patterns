package victor.training.patterns.creational.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer {
	private Long id;
	private String name; // NAME REQUIRED
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	private Customer() {
	}

	public Customer(String name, Address address) {
		this.address = Objects.requireNonNull(address);
		if (name.length() < 5) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	public Customer(Long id, String name, String phone, List<String> labels, Address address, Date createDate) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.labels = labels;
		this.address = address;
		this.createDate = createDate;
	}

	public Customer withLabels(List<String> labels) {
		return new Customer(id, name, phone, labels, address, createDate);
	}

//	public static class Builder {
//		private final Customer customer = new Customer();
//    public Builder(required fields) {}
//		public Builder withName(String name) {
//			if (name.length() < 5) {
//				throw new IllegalArgumentException();
//			}
//			customer.name = name;
//			return this;
//		}
//
//		public Builder withLabels(List<String> labels) {
//			customer.labels = labels;
//			return this;
//		}
//
//		public Builder withAddress(Address address) {
//			customer.address = address;
//			return this;
//		}
//
//		public Customer build() {
//			Objects.requireNonNull(customer.name);
//			return customer;
//		}
//	}


	public Address getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}


	public String getPhone() {
		return phone;
	}

	public List<String> getLabels() {
		return labels;
	}

	public Date getCreateDate() {
		return createDate;
	}

}
