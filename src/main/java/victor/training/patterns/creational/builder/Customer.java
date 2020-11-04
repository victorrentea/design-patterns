package victor.training.patterns.creational.builder;

import lombok.Getter;
import lombok.Setter;
import victor.training.patterns.stuff.pretend.Entity;

import java.util.*;

@Entity
public class Customer {
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String phone;
	private List<String> labels = new ArrayList<>();
	@Getter @Setter
	private Address address;
	private Date createDate;

	public List<String> getLabels() {
		return Collections.unmodifiableList(labels);
	}

	// test-friendlied mutator in my sacred entity
	public Customer addLabel(String... labels) {
		this.labels.addAll(Arrays.asList(labels));
		return this;
	}
}
