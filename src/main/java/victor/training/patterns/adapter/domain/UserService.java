package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserApiClient;

import java.util.List;

@Slf4j
@Service // holy domain ZEN PEACE HARMONY for my core logic.
public class UserService {
	@Autowired
	private LdapUserApiClient apiClient;

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = apiClient.search(null, null, username.toUpperCase());

		if (list.size() != 1) {
			throw new IllegalArgumentException("There is  MARIO no single user matching username " + username);
		}

		LdapUserDto ldapUser = list.get(0);
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		User user = new User(ldapUser.getuId(), ldapUser.getWorkEmail(), fullName);

		deepDomainLogic(user);

	}

	private void deepDomainLogic(User ldapUser) { // to many fields in the dto
		if (ldapUser.hasWorkEmail()) { // add behavior
			log.debug("Send welcome email to  " + ldapUser.workEmail());
		}

		log.debug("Insert user in my database: " + ldapUser.username()); // MY names

		log.debug("More business logic with " + ldapUser.fullName() + " of id " +
				  ldapUser.username().toLowerCase()); // never null
	}

//	private static void innocentMethodNeverSuspectedToSideEffect(User ldapUser) {
//		// dark dark night coding alone: a "quick fix"
//		if (ldapUser.id() == null) { // no setter
//			ldapUser.setuId("N/A"); // BAD: altering
//		}
//	}

}
