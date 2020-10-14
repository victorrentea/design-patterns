package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

// ZEN
// ZEN
// ZEN
// ZEN
// ZEN
// ZEN
@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private LdapServiceAdapter adapter;

	public void importUserFromLdap(String username) {
		List<User> list = adapter.searchByUserName(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}
	// 200 lines below:

	public List<User> searchUserInLdap(String username) {
		return adapter.searchByUserName(username);
	}

	

}
