package victor.training.patterns.creational.builder;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private Long id;
	private String fullName;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;
	
	
	
public Customer() {}
	public Customer(String fullName, String phone, Address address) {
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public Customer setAddress(Address address) {
		this.address = address;
		return this;
	}

	public String getFullName() {
		return fullName;
	}
	
	
	// REQUIRED FIELDS vs OPTIONAL FIELS

	public Customer setFullName(String newFullName) {
		this.fullName = newFullName;
		return new Customer(newFullName, phone, address)
				.addLabels(labels.toArray(new String[0]));
//		this;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Customer addLabels(String... newLabels) {
		labels.addAll(asList(newLabels));
		return this;
	}

}
