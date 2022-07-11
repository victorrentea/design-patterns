package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.util.pretend.Entity;

import java.time.LocalDate;

@Data
@Entity
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String email;
	private Site site;
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
		if (isGoldMember()) {
			discountPercentage += 1;
		}
		// + 7 linii de cod - inca e ok.
		// + 27 linii de cod, eventual cu acces la @Value properties - scoti intr-un @Service bine crescut
		return discountPercentage;
	}

//	public CustomerDto toDto() { // gunoi. mizerie. DTO. prezentare. formatre.
//		// Nu IN CEL MAI SFANT LOC DIN APP TA: Domnain @ENtity
//
//	}
}
