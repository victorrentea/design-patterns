package victor.training.patterns.structural.adapter.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

@Component
public class LdapServiceAdapter {
	@Autowired
	private LdapUserWebserviceClient wsClient;

	public List<User> searchByUserName(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertFromLdap)
				.collect(toList());
	}

	private User convertFromLdap(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}
}