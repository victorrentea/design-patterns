package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	@Size(min = 3)
	@NotNull
	private String name;
	private String email;
	private Site site;
	private LocalDate creationDate;
	private boolean goldMember;

	public Customer(String name) {
		if (name.length() < 5) {
			throw new IllegalArgumentException("Valeu!");
		}
		this.name = Objects.requireNonNull(name);
	}

	public int getDiscountPercentage() {
		int discountPercentage = 3;
		if (isGoldMember()) {
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
//
//	public CustomerDto toDto() {
//		CustomerDto jeg = new CustomerDto();
//		dto.name = customer.getName();
//		dto.email = customer.getEmail();
//		dto.creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
//		dto.id = customer.getId();
//	}
}
