package victor.training.patterns.creational.builder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
}
