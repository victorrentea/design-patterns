package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
// Biz logic `zen armonie ying si yang
public class UserService {
	@Autowired
	private ILdapServiceAdapter adapter;

	public void importUserFromLdap(String username) {
		User user = adapter.retrieveUserByUsername(username);

//		LdapUserDto dtoDusman = adapter.neatent();

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		// logica GREA
		log.debug("Insert user in my database");
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}
}

