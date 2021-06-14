package victor.training.patterns.structural.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String email;
	private Site site;
	private Date creationDate;

	public int computeDiscount() {
		int discount = 2;
		if (getCreationDate().getYear() < 2017) {
			discount += 1;
		}
		return discount;
	}
}
