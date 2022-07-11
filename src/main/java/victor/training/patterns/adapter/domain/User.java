package victor.training.patterns.adapter.domain;

// This would normally be placed in a 'domain model' package
// i'm sorry
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public User(String username, String fullName, String workEmail) {
		this.username = username;
		this.fullName = fullName;
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
