package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

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
