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

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}
}
