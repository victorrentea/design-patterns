package victor.training.patterns.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.infra.LdapUserDto;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

	private final LdapUserWebserviceClient wsClient;

	public UserService(LdapUserWebserviceClient wsClient) {
		this.wsClient = wsClient;
	}

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		LdapUserDto ldapUser = list.get(0);
		User user = fromDto(ldapUser);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}

	public List<User> searchUserInLdap(String username) {
		List<LdapUserDto> list = searchByUsername(username);
		List<User> results = new ArrayList<>();
		for (LdapUserDto ldapUser : list) {
			User user = fromDto(ldapUser);
			results.add(user);
		}
		return results.subList(0, 5);
	}

	// ------------------------------

	private User fromDto(LdapUserDto ldapUser) {
		String fullName = toCorporateName(ldapUser);
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private String toCorporateName(LdapUserDto ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return fullName;
	}

	private List<LdapUserDto> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null);
	}

}
