package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserService {
	private final LdapUserServiceAdapter adapter;

	public UserService(LdapUserServiceAdapter adapter) {
		this.adapter = adapter;
	}

	public void importUserFromLdap(String username) {
		List<User> list = adapter.findUsersByUsername(username);
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
		return adapter.findUsersByUsername(username);
	}

	// DEASUPRA LINEI: ZEN SI PACE, YING SI YANG, FENG SI SHUI
	// -- o linie ---------------------------------
	// SUB LINE: GUNOI


}
