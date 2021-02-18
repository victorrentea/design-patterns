package victor.training.patterns.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService { // the holy ground
	private final ILdapAdapter ldapAdapter;

	public void importUserFromLdap(String username) {
		List<User> list = ldapAdapter.searchByUsername(username);
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
		return ldapAdapter.searchByUsername(username);
	}

	// ------------------------- o linie -------------------------
	// fa te rog ca tot ce nu e al nostru sa shada sub linie

	// TODO @end: Archunit!
}
