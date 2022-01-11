package victor.training.patterns.creational.builder;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer {
	private final Long id;
	private final String name;
	private final String phone;
	private final List<String> labels;
	private final Address address;
	private final Date createDate;

	public Customer(Long id, String name, String phone, List<String> labels, Address address, Date createDate) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.labels = labels;
		this.address = address;
		this.createDate = createDate;
	}

	public Customer(Long id, String name, Date createDate) {
		this(id, name, null, Collections.emptyList(), null, createDate);
		Objects.requireNonNull(name);
	}


	public Long getId() {
		return id;
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

	public Address getAddress() {
		return address;
	}

	public Date getCreateDate() {
		return createDate;
	}


	// "wither"
	public Customer withLabels(List<String> newLabels) {
		return new Customer(id, name, phone, newLabels, address, createDate);
	}

	public Customer withAddress(Address address) {
		return new Customer(id, name, phone, labels, address, createDate);
	}
}
