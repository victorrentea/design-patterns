package victor.training.oo.structural.facade.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
	@Getter
	private Long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private Site site;
	private List<Address> addresses = new ArrayList<>();

	public void addAddress(Address address) {
		addresses.add(address);
		address.setCustomer(this);
	}
	public List<Address> getAddresses() {
		return Collections.unmodifiableList(addresses);
	}
}
class Address {
	@Getter
	@Setter
	private Customer customer;

}
