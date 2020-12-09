package victor.training.patterns.structural.adapter.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final IAdapter adapter;

	public UserService(IAdapter adapter) {
		this.adapter = adapter;
	}

	public void importUserFromLdap(String username) {
		List<User> list = adapter.searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	// 200 de linii mai jos:
	public List<User> searchUserInLdap(String username) {
		return adapter.searchByUsername(username);
	}

	// TODO vreau sa scot cu totul din clasa asta orice urma de LdapUser intr-o alta clasa, Adapter


}
