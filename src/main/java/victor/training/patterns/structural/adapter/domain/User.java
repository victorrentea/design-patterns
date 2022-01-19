package victor.training.patterns.structural.adapter.domain;

// This would normally be placed in a 'domain model' package
public class User {
	private String username;
	private String fullName;
	private String workEmail;

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
