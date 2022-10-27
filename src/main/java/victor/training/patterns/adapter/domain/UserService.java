package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserApiClient;

import java.util.List;

@Slf4j
@Service // ZEN garden, my core domain logic
public class UserService {
	@Autowired
	private LdapUserApiClient apiClient;

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = apiClient.search(username.toUpperCase(), null, null);

		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}

		LdapUserDto ldapUser = list.get(0);

		deepDomainLogic(ldapUser);
	}

	private void deepDomainLogic(LdapUserDto ldapUser) {
		log.debug("Insert user in my database: " + ldapUser.getuId());

		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		log.debug("More business logic with " + fullName + " of id " + ldapUser.getuId().toLowerCase());

		if (ldapUser.getWorkEmail()!=null) {
			String toRecipient = fullName + "<" + ldapUser.getWorkEmail() + ">"; // repeats in the code
			log.debug("Send welcome email to  " + toRecipient);
		}
	}

}
