package victor.training.oo.structural.adapter.external;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import victor.training.oo.structural.adapter.domain.ILdapUserRepository;
import victor.training.oo.structural.adapter.domain.User;

@Component
public class LdapUserRepository implements ILdapUserRepository {
	@Autowired
	private LdapUserWebserviceClient wsClient;
	
	public List<User> search(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::mapLdapUser)
				.collect(toList());
	}
	
	
	private User mapLdapUser(LdapUser ldapUser) {
		String fullName = extractFullName(ldapUser);
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}
	
	private String extractFullName(LdapUser ldapUser) {
		return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
	}
}