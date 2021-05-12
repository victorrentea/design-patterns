package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
// ESTI IN GRADINA IMPARATULUI
public class UserService {
	@Autowired
	private ExternalServiceAdapter adapter;

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
		List<User> list = adapter.searchByUsername(username);
		return list.subList(0, 5);
	}

	// -------------------------------------------------------------------------------------------------------
	// Arhitectura este arta de a trage linii


	// TODO @end: Archunit!
}
