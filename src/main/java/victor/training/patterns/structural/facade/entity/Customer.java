package victor.training.patterns.structural.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	@Size(min = 3)
	private String name;
	private String email;
	private Site site;
	@NotNull
	private LocalDate creationDate;
	private boolean goldMember;

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}

	public int getDiscountPercentage() {
		int discountPercentage = 3;
		if (goldMember) {
			discountPercentage += 1;
		}
		return discountPercentage;
	}
}
