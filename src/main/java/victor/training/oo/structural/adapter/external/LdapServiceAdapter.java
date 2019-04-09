package victor.training.oo.structural.adapter.external;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

@Component 
public class LdapServiceAdapter implements ILdapServiceAdapter {
	@Autowired
	private LdapUserWebserviceClient wsClient;
	
	public List<User> search(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::convertUser)
				.collect(toList());
	}
	
	private User convertUser(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}

}