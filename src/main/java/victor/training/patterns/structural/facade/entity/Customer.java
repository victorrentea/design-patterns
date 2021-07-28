package victor.training.patterns.structural.facade.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

@Data
public class Customer {
	@Setter(AccessLevel.NONE)
	private Long id;
//	@Min(5)
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
		return discountPercentage;
	}

//	private Customer() {} // pt hibernate
//	public Customer(String name) {
//		if (name.trim().length() <= 5) {
//			throw new IllegalArgumentException("Name too short");
//		}
//		this.name = name;
//	}
//	public CustomerDto toDto() {} // !! anathema

	static {
		Set<String> strings = new HashSet<>();
		strings.iterator().next();

//		for (String string : strings) {
		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			String string = iterator.next();
			System.out.println(string);
		}
		Stream<String> a = Stream.of("a", "b");
//		a.iterator()/
	}
}
