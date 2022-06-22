package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
	@Autowired
	private ExtrernalUserService extrernalUserService;

	public void importUserFromLdap(String username) {
		User user = extrernalUserService.retrieveByUsername(username);

		if (user.hasEmail()) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
		log.debug("Create user profile");
		log.debug("Check user status in permission manager");
	}


}
