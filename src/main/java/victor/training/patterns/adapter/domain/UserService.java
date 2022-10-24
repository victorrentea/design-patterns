package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service // holy domain ZEN PEACE HARMONY for my core logic.
public class UserService {
	@Autowired
	private UserApiAdapter userAPiAdapter;

	public void importUserFromLdap(String username) {
		User user = userAPiAdapter.fetchUser(username); // scream: NETWORK! don't call me in a loop!!

		// to many fields in the dto
		if (user.hasWorkEmail()) { // add behavior
			log.debug("Send welcome email to  " + user.workEmail());
		}

		log.debug("Insert user in my database: " + user.username()); // MY names

		log.debug("More business logic with " + user.fullName() + " of id " +
				  user.username().toLowerCase()); // never null

	}


	//	private static void innocentMethodNeverSuspectedToSideEffect(User ldapUser) {
//		// dark dark night coding alone: a "quick fix"
//		if (ldapUser.id() == null) { // no setter
//			ldapUser.setuId("N/A"); // BAD: altering
//		}
//	}

}
