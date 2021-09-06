package victor.training.patterns.structural.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
//	@NotNull//(groups = SUBMITTED.class)
//	@Min(6)

	//	@CustomerName
	private String name;
	@Email//(groups = {DRAFT.class, SUBMITTED.class})
	private String email;
	private Site site;
	private Long siteId;
	private LocalDate creationDate;
	private boolean goldMember;
//	private String field100; // :)

//	public Customer(String name) {
//		setName(name);
//	}

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}

	// smart entity (aka Rich Entity Model)
	public int getDiscountPercentage() {
		int discountPercentage = 3;
		if (goldMember) {
			discountPercentage += 1;
		}
		return discountPercentage;
	}

//	public Customer setName(String name) {
//		if (name.length() <= 5) {
//			throw new IllegalArgumentException("Name too short");
//		}
//		this.name = name;
//		return this;
//	}
}
