package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import victor.training.patterns.facade.facade.dto.CustomerDto;

import java.time.LocalDate;

@Data
//@Document
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String email;
	private Site site;
	private LocalDate creationDate;
	private boolean goldMember;

	// Clean/Onion/Hexagonal Arch
	// NEVER because the CORE should know nothing about the APIs
//    public static Customer fromDto(CustomerDto dto, Site site) {
//        Customer customer = new Customer();
//        customer.setEmail(dto.email);
//        customer.setName(dto.name);
//        customer.setSite(site);
//        return customer;
//    }

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
		return discountPercentage;
	}
}
