package victor.training.patterns.creational.builder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Value
@Builder
public class Customer {
	private final Long id;
	private final String name;
	@With
	private final String phone;
	private final List<String> labels;
	private final Address address;
	@With
	private final Date createDate;

	/// which are MANDATORY ?

	public Customer(Long id, String name, List<String> labels, Address address) {
		this(Objects.requireNonNull(id), Objects.requireNonNull(name), null, labels, address, null);
		name.toUpperCase()
		;
	}

	public Customer(Long id, String name, String phone, List<String> labels, Address address, Date createDate) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.labels = labels;
		this.address = address;
		this.createDate = createDate;
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
		return Collections.unmodifiableList(labels);
	}

	public Address getAddress() {
		return address;
	}

	public Date getCreateDate() {
		return createDate;
	}

//	public Customer withPhone(String newPhone) {
//		return new Customer(id, name, newPhone, labels, address, createDate);
//	}
}
