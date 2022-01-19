package victor.training.patterns.structural.adapter.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	private final ExternalUserService adapter;

	public UserService(ExternalUserService adapter) {
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
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}

	public List<User> searchUserInLdap(String username) {
		List<User> list = adapter.searchByUsername(username);

		return list.subList(0, 5);
	}
}
