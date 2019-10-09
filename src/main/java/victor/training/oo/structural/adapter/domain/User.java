package victor.training.oo.structural.adapter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import victor.training.oo.structural.adapter.external.LdapUser;

// This would normally be placed in a 'domain model' package
@Data // i'm sorry
public class User {
	private String username;
	private String fullName;
	private String workEmail;

	public User(String username, String fullName, String workEmail) {
		this.username = username;
		this.fullName = fullName;
		this.workEmail = workEmail;
	}
}

