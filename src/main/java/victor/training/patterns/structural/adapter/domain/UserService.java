package victor.training.patterns.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService { // the holy ground
	private final LdapUserWebserviceClient wsClient;

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

	// 200 de linii mai jos
	public List<User> searchUserInLdap(String username) {
		return searchByUsername(username);
	}

	// ------------------------- o linie -------------------------
	// fa te rog ca tot ce nu e al nostru sa shada sub linie


	private User convertToEntity(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null)
			.stream()
			.map(this::convertToEntity)
			.collect(toList());
	}

	// TODO @end: Archunit!
}
