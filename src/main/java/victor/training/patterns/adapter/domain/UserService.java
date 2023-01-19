package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service // ZEN GARDEN
public class UserService {
	@Autowired
	private ExternalUserProvider userProvider;

	public void importUserFromLdap(String username) {
		User user = userProvider.fetchUser(username);
		if (user.workEmail()!=null) { // can't put logic in the domain
			log.debug("Send welcome email to  " + user.workEmail());
		}


		log.debug("Insert user in my database: " + user.username()); // bad names

		innocent(user);// temporal coupling with the next line: they have to happen in this order,
		// although nothign tells me that. why ? setters (mutable data)
		log.debug("More business logic with " + user.fullName() + " of id " + user.username().toLowerCase()); // NPE rislk/ invalid data

	}


	private void innocent(User ldapUser) { // Side effects on params.
//		if (ldapUser.getuId() == null) { // ha ha ha you can't immutable
//			ldapUser.setuId("N/A");
//		}
	}

}
