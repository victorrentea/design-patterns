package victor.training.patterns.creational.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private Long id;
	private String name;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	public Long getId() {
		return id;
	}

	public Customer setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Customer setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public List<String> getLabels() {
		return labels;
	}

	public Customer setLabels(List<String> labels) {
		this.labels = labels;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Customer setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Customer setAddress(Address address) {
		this.address = address;
		return this;
	}
}
