package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import static java.util.stream.Collectors.toList;

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
	private final LdapUserWebserviceClient wsClient;

	public UserService(LdapUserWebserviceClient wsClient) {
		this.wsClient = wsClient;
	}

	public void importUserFromLdap(String username) {
		List<User> list = searchByUserName(username);
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
		return searchByUserName(username);
	}

	// ZEN ABOVE
	/// ----------------------- CAREFUL. GARBAGE BELOW ---------------------
	// You who enter, abandon all hope


	private List<User> searchByUserName(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertFromLdap)
				.collect(toList());
	}
	
	private User convertFromLdap(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}
	
}
