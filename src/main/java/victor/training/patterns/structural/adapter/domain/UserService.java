package victor.training.patterns.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService { // the holy ground
	private final LdapUserWebserviceClient wsClient;

	public void importUserFromLdap(String username) {
		List<LdapUser> list = searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		LdapUser ldapUser = list.get(0);
		User user = convertToEntity(ldapUser);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	// 200 de linii mai jos
	public List<User> searchUserInLdap(String username) {
		List<LdapUser> list = searchByUsername(username);
		List<User> results = new ArrayList<>();
		for (LdapUser ldapUser : list) {
			User user = convertToEntity(ldapUser);
			results.add(user);
		}
		return results.subList(0, 5);
	}

	private User convertToEntity(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private List<LdapUser> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null);
	}

	// TODO @end: Archunit!
}
