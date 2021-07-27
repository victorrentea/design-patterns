package victor.training.patterns.structural.adapter.domain;

import lombok.Data;

// This would normally be placed in a 'domain model' package
@Data // i'm sorry
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public boolean hasEmail() {
		return workEmail != null;
	}
}
