package victor.training.patterns.structural.adapter.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService { // DOmain Logic ! Gradina imparatului. ZEN . Ying si Yang.
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private LdapUserServiceAdapter ldapUserServiceAdapter;

	public void importUserFromLdap(String username) {
		User user = ldapUserServiceAdapter.findOneByUsername(username);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}

}
