package victor.training.patterns.creational.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String phone;
	@Setter(AccessLevel.NONE)
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	public Customer addLabels(String... labelsArr) {
		labels.addAll(Arrays.asList(labelsArr));
		return this;
	}
}
