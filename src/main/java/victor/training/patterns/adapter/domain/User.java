package victor.training.patterns.adapter.domain;

import java.util.Objects;

// This would normally be placed in a 'domain model' package
// i'm sorry
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public User(String username, String fullName, String workEmail) {
		this.username = Objects.requireNonNull(username);
		this.fullName = Objects.requireNonNull(fullName);
		this.workEmail = workEmail;
	}

	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public String getWorkEmail() {
		return workEmail;
	}

}
