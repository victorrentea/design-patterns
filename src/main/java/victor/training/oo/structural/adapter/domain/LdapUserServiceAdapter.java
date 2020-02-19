package victor.training.oo.structural.adapter.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

public class LdapUserServiceAdapter {
	private final LdapUserWebserviceClient wsClient;
	
	public LdapUserServiceAdapter(LdapUserWebserviceClient wsClient) {
		this.wsClient = wsClient;
	}
	public List<User> findUsersByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertLdapUser)
				.collect(toList());
	}
	private User convertLdapUser(LdapUser ldapUser) {
		String fullName = getFullName(ldapUser);
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

	private String getFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}

}