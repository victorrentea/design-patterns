package victor.training.oo.creational.builder;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class Customer {

	private String name;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	// 2-3 helper methods pt teste
	public Customer addLabels(String... labels) {
		this.labels.addAll(Arrays.asList(labels));
		return this;
	}
}
