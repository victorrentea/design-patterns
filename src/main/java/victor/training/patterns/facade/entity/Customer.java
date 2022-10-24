package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String email;
	private Site site;
	private LocalDate creationDate;
	private boolean goldMember;

    public int getDiscountPercentage() {
		// OOP: keep bits(<7 lines) of REUSABLE DOMAIN behavior next to state. = ❤️ >> DDD
        int discountPercentage = 3;
		if (goldMember) {
            discountPercentage += 1;
        }
        return discountPercentage;
    }

    public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}
}
