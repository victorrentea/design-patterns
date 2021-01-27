package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
// YOu who enter, be careful. ZEN LOGIC is here .
// Peace harmony... DOMAIN LOGIC.
public class UserService {
	private final LdapUserWebserviceClient wsClient;

	public UserService(LdapUserWebserviceClient wsClient) {
		this.wsClient = wsClient;
	}

	public void importUserFromLdap(String username) {
		List<User> list = searchByUsername(username);
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
		return searchByUsername(username);
	}

	// Heaven
	// ------------------- a line --------------------------------
	// gabage . Dante's Inferno
	private User convert(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null)
			.stream().map(this::convert).collect(toList());
	}

}
