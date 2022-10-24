package victor.training.patterns.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserApiClient;

import java.util.List;

@Slf4j
@Service
public class UserService {
	@Autowired
	private LdapUserApiClient apiClient;

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = apiClient.search(null, null, username.toUpperCase());

		if (list.size() != 1) {
			throw new IllegalArgumentException("There is  MARIO no single user matching username " + username);
		}

		LdapUserDto ldapUser = list.get(0);

		deepDomainLogic(ldapUser);

	}

	private void deepDomainLogic(LdapUserDto ldapUser) {
		if (ldapUser.getWorkEmail()!=null) {
			log.debug("Send welcome email to  " + ldapUser.getWorkEmail());
		}

		log.debug("Insert user in my database: " + ldapUser.getuId());

		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		log.debug("More business logic with " + fullName + " of id " + ldapUser.getuId().toLowerCase());
	}

}
