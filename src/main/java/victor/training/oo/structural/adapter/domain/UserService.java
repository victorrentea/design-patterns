package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
// Suntem in cel mai sacru loc din app noastra: in Domain Service, Unde ar trebui sa fie cea mai pura si frumoasa logica posibila
public class UserService {
	private final LdapUserWebserviceClient wsClient;


	// viata-i grea, n-o poate toti
	public void importUserFromLdap(String username) {
		List<LdapUser> list = searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		LdapUser ldapUser = list.get(0);
		User user = convert(ldapUser);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}
	
	public List<User> searchUserInLdap(String username) {
		List<LdapUser> list = searchByUsername(username);
		List<User> results = new ArrayList<>();
		for (LdapUser ldapUser : list) {
			User user = convert(ldapUser);
			results.add(user);
		}
		return results;
	}

	private User convert(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private List<LdapUser> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null);
	}

}
