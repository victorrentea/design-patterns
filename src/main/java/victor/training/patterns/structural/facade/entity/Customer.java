package victor.training.patterns.structural.facade.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class Customer {
	private Long id;
	@Size(min = 5)
	@NotNull
	private String name;
	private String email;
	private Site site;
	private Date creationDate;
}
