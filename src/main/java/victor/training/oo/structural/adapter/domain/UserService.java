package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j

// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
public class UserService {
	private LdapUserWebserviceClient wsClient;

	public void importUserFromLdap(String username) {
		List<User> list = searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}
	
	public List<User> searchUserInLdap(String username) {
		return searchByUsername(username);
	}
	// BUN
	// ---------------- LINIE ---------------------
	// INFERNUL lui Dante
	// Tu cel ce intri, abandoneaza orice speranta...

	private User buildUser(LdapUser ldapUser) {
		String fullName = extractFullName(ldapUser);
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private String extractFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}

	private List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::buildUser)
				.collect(toList());
	}

}
