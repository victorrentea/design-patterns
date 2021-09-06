package victor.training.patterns.creational.builder;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.*;


@Builder
@Value // == @Data + all fields private final. @Value is better than @Data
class ImmutableObj {
	String a;
	String b;
}

@Data
public class Customer {
	private Long id;
	//	@NotNull
	private String name;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;
	private Date createDate;

	public Customer() {
	} // for love of Hibernate

	public Customer(String name) {
		setName(name);
	}

	public Customer setName(String name) {
		this.name = Objects.requireNonNull(name);
		return this;
	}

	public Customer addLabels(String... labelsToAdd) {
		labels.addAll(Arrays.asList(labelsToAdd));
		return this;
	}
}

// How many fiels has that big entity of yours.
// 100  - 23 mandatory
// 25 fields - 5 mandatory
