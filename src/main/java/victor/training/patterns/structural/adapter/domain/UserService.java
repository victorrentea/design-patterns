package victor.training.patterns.structural.adapter.domain;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // DOMAIN LOGIC
// Emperor's garden. THE ZEN PLACE.
public class UserService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
	private final ExternalUserService ldapAdapter;

	public UserService(ExternalUserService ldapAdapter) {
		this.ldapAdapter = ldapAdapter;
	}

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
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}


}
