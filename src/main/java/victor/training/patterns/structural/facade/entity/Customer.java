package victor.training.patterns.structural.facade.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
	private Long id;
	private String name;
	private String email;
	private Site site;
	private Date creationDate;
}
