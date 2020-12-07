package victor.training.patterns.creational.builder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public class Customer {
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String phone;
	@Getter
	private List<String> labels = new ArrayList<>();
	@Getter @Setter
	private Address address;
	@Getter @Setter
	private Date createDate;

	public Customer addLabels(String... labels) {
		this.labels.addAll(asList(labels));
		return this;
	}
}
