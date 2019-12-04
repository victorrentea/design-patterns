package victor.training.oo.structural.adapter.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

@Slf4j
@Service
// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// Loc de verdeata, loc de racoare, de unde a fugit toata intristarea si suspinul.
public class UserService {
	@Autowired
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
	// linie -----------------------
	// Tu cel ce intri, abandoneaza orice speranta

	// aici: GUNOI

	private User mapUserFromLdap(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::mapUserFromLdap)
				.collect(Collectors.toList());
	}

}
