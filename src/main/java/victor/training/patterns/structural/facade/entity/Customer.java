package victor.training.patterns.structural.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import victor.training.patterns.stuff.pretend.Entity;

import java.time.LocalDate;

// NO @Data
// YES @Value (or records)
// YES @Slf4j
// YES @RequiredArgsConsturcotr on @Service
// NO @Setter,  NO @Builder , var, val
@Data
@Entity
// if entity model freezes, the app starts dying.
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String email;
	//	private UserIdentificatiob;
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
