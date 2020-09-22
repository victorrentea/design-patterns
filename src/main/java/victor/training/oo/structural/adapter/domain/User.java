package victor.training.oo.structural.adapter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import victor.training.oo.stuff.pretend.Entity;

// This would normally be placed in a 'domain model' package
@Data // i'm sorry
@AllArgsConstructor
@Entity
public class User {
	private String username;
	private String fullName;
	private String workEmail;


	// o structura de
//	private LdapUser ldapUser;
}
