package victor.training.patterns.adapter.domain;

import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Objects;

// This would normally be placed in a 'domain model' package
// i'm sorry
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public User(String username, String fullName, String workEmail) {
		if (username.length() == 0) {
			throw new IllegalArgumentException("Marsh!");
		}
		this.username = username;
		this.fullName = fullName;
		this.workEmail = Objects.requireNonNull(workEmail);
	}

	boolean hasEmail() {
		return getWorkEmail() != null;
	}

	public String getUsername() {
		return this.username;
	}

	public String getFullName() {
		return this.fullName;
	}

	public String getWorkEmail() {
		return this.workEmail;
	}

	public String toString() {
		return "User(username=" + this.getUsername() + ", fullName=" + this.getFullName() + ", workEmail=" + this.getWorkEmail() + ")";
	}
}
