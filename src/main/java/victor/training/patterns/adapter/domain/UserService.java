package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserApiClient;

import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@Service // ZEN GARDEN
public class UserService {
	@Autowired
	private LdapUserApiClient apiClient;

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = apiClient.search(username.toUpperCase(), null, null);

		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}

		LdapUserDto ldapUser = list.get(0);
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase(); // mapping spread in my domain

		User user = new User(ldapUser.getuId(), fullName, ofNullable(ldapUser.getWorkEmail()));

		deepDomainLogic(user);

	}

	private void deepDomainLogic(User user) {
		if (user.workEmail().isPresent()) { // can't put logic in the domain
			log.debug("Send welcome email to  " + user.workEmail().get());
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
