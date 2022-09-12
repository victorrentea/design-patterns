package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service // pace/ zen armonie biz rules, fara nulluri, fara invalide,
public class UserService {
	@Autowired
	private LdapClientAdapter adapter;

	public void importUserFromLdap(String username) {
		User user = adapter.findByUsername(username);

		if (user.hasEmail()) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}




}
