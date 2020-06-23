package victor.training.oo.creational.builder;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import victor.training.oo.stuff.pretend.Entity;

import java.util.*;

@Entity
//@Data
class Order {
//	@ManyToOne
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	void setCustomer(Customer customer) {
		this.customer = customer;
//		customer.addOrder(this);
	}
}

@Entity
public class Customer {
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String phone;
	@Getter @Setter
	private List<String> labels = new ArrayList<>();
	@Getter @Setter
	private Address address;
	@Getter
	private Date createDate;
//	@OneToMany(mappedBy = "customer")
//	@Getter @Setter
	private List<Order> orders = new ArrayList<>();
	private int totalOrderAmount;

	protected Customer() {} // pentru hibernate
	public Customer(String name) {
		setName(name);
	}

	public Customer setName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("blank name");
		}
		this.name = name;
		return this;
	}

	// forma clasica, riscant ca la runtime poc
	public List<Order> getOrders() {
		return Collections.unmodifiableList(orders);
	}
	public Iterable<Order> getOrdersIterable() {
		return orders;
	}
	public Collection<? extends Order> getOrdersGeneric() {
		return orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
		order.setCustomer(this);
		// TODO totalOrderAmount +=
	}
	// TODO remove


	//
//	public String getName() {
//		return name;
//	}
//
//	public Customer setName(String name) {
//		this.name = name;
//		return this;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public Customer setPhone(String phone) {
//		this.phone = phone;
//		return this;
//	}
//
//	public List<String> getLabels() {
//		return labels;
//	}
//
//	public Customer setLabels(List<String> labels) {
//		this.labels = labels;
//		return this;
//	}
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public Customer setAddress(Address address) {
//		this.address = address;
//		return this;
//	}
//
//	public Date getCreateDate() {
//		return createDate;
//	}
//
//	public Customer setCreateDate(Date createDate) {
//		this.createDate = createDate;
//		return this;
//	}
}
