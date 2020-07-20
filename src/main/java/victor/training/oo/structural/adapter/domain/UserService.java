package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
	@Autowired
	private LdapServiceAdapter ldapServiceAdapter;

	public void importUserFromLdap(String username) {
		List<User> list = ldapServiceAdapter.searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);
		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	//200 linii mai jos
	public List<User> searchUserInLdap(String username) {
		return ldapServiceAdapter.searchByUsername(username);
	}

	// codu meu. My preciousssss..... ZEN PACE ARMONIE
	// -- o linie ----------------------------------------------------------------
	// PURGATORIUL.
	// tu cel ce intra, abandoneaza orice speranta

}
