package victor.training.patterns.adapter.domain;

import java.util.Objects;

// This would normally be placed in a 'domain model' package
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public User(String username, String fullName, String workEmail) {
		this.username = Objects.requireNonNull(username);
		if (fullName.length() < 3) {
			throw new IllegalArgumentException();
		}
		this.fullName = fullName;
		this.workEmail = workEmail;
	}

	public boolean hasEmail() {
		return workEmail != null;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUsername() {
		return username;
	}

	public String getWorkEmail() {
		return workEmail;
	}
}
