package victor.training.patterns.creational.builder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Builder
@Setter
@Getter
public class Customer {
	private Long id;
	@NotNull
	private String name;
	private String phone;
	private List<String> labels = new ArrayList<>();
	private Address address;

//	private AuditTrail created; //{date, user}
//	private AuditTrail modified;

//	private String createdBy;
//	private Date createDate;

//	public Customer(String name, a,a,a,aa,a) {
//		setName(name);
//	}
//
//	public Customer setName(String name) {
//		this.name = Objects.requireNonNull(name);
//		return this;
//	}

	public Customer addLabels(String... newLabels) {
		labels.addAll(Arrays.asList(newLabels));
		return this;
	}
}


@Service
class SomeService {
	public void method(@Validated Customer customer) {

	}

	public void method2(Customer customer) {
//		repo.save(customer); // hibernate automat valideaza aceste adnotari
	}
}