package victor.training.patterns.creational.builder;

import lombok.Data;

import java.util.ArrayList;
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

}
