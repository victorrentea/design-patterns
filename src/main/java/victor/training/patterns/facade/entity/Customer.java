package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import victor.training.patterns.facade.facade.dto.CustomerDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	@Setter(AccessLevel.NONE)
	private String name;
	private String email;
	private Site site;
	private LocalDate creationDate;
	private boolean goldMember;

	public int getDiscountPercentage() {
		int discountPercentage = 3;
		if (isGoldMember()) {
			discountPercentage += 1;
		}
		return discountPercentage;
	}

	public void setName(String name) {
		if (name.length() < 4) {
			throw new IllegalArgumentException("Valeu!");
		}
		this.name = Objects.requireNonNull(name);
	}

	public Customer(String name) {
		setName(name);
	}

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}
//
//	public CustomerDto toDto() {
//		CustomerDto dto = new CustomerDto();
//		dto.name = getName();
//		dto.email = getEmail();
//		dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(getCreationDate());
//		dto.id = getId();
//		return dto;
//	}
}
