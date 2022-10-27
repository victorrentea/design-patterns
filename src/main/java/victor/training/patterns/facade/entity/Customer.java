package victor.training.patterns.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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

    public int getDiscountPercentage() {
		// Codesmell = Feature Evny
		// just a bit(3-7 lines) of domain logic working with the state
		// of a single Domain object, and potentially reusable
        int discountPercentage = 3;
		if (goldMember) {
            discountPercentage += 1;
        }
        return discountPercentage;
    }
    // @OneToMany
//	@JsonIgnore
//	private List<Child> lazyLoadedChildrenCollection; // jackson might trigger LAZY LOAD a children collection

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}

	// polluting the most sacred code you have with shitty DTOs! Never!
	// your Domain Entity should not know about APIs
//	public CustomerDto toDto() {
//		throw new RuntimeException("Method not implemented");
//	}
}
