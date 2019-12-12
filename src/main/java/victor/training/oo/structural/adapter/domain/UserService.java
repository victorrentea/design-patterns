package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
// HOLY SACRED ZEN PEACE HARMONY
// HOLY SACRED ZEN PEACE HARMONY
// HOLY SACRED ZEN PEACE HARMONY
// HOLY SACRED ZEN PEACE HARMONY
// HOLY SACRED ZEN PEACE HARMONY
public class UserService {
	private final LdapUserWebserviceClient wsClient;

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

	// dining room
	// a line ---------------------------------------
	// laundry room

	private User convertFrom(LdapUser ldapUser) {
		return new User(ldapUser.getuId(), mapFullName(ldapUser), ldapUser.getWorkEmail());
	}

	private String mapFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}

	private List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertFrom).collect(toList());
	}

}
