package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j

// ZEN
public class UserService {
	private final UserServiceAdapter adapter;

	public UserService(UserServiceAdapter adapter) {
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

	public List<User> searchUserInLdap(String username) {
		return adapter.searchByUsername(username);
	}

}
