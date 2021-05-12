package victor.training.patterns.creational.builder;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class Customer {
	private Long id;
	private String name;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	public Customer addLabels(String... labels) {
		this.labels.addAll(Arrays.asList(labels));
		return this;
	}
}
