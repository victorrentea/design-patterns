package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserService {
	private final LdapUserWebserviceClient wsClient;

	public UserService(LdapUserWebserviceClient wsClient) {
		this.wsClient = wsClient;
	}

	public void importUserFromLdap(String username) {
		List<User> list = findUsersByUsername(username);
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
		return findUsersByUsername(username);
	}
	
	// DEASUPRA LINEI: ZEN SI PACE, YING SI YANG, FENG SI SHUI
	// -- o linie ---------------------------------
	// SUB LINE: GUNOI
	
	

	private User convertLdapUser(LdapUser ldapUser) {
		String fullName = getFullName(ldapUser);
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private String getFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}

	private List<User> findUsersByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertLdapUser)
				.collect(toList());
	}
	
}
