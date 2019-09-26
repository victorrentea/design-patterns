package victor.training.oo.structural.adapter.external;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import victor.training.oo.structural.adapter.domain.IUserWsClientAdapter;
import victor.training.oo.structural.adapter.domain.User;

public class UserWsClientAdapter implements IUserWsClientAdapter {
	
	@Autowired
	private LdapUserWebserviceClient wsClient;

	private User convertUser(LdapUser ldapUser) {
		return new User(ldapUser.getuId(), getFullName(ldapUser), ldapUser.getWorkEmail());
	}

	public List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(ldapUser -> convertUser(ldapUser))
				.collect(toList());
	}

	private String getFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}
}