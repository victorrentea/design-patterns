package victor.training.oo.structural.adapter.external;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

@Service
public class LdapServiceAdapter implements ILdapServiceAdapter {  
	@Autowired
	private LdapUserWebserviceClient wsClient;

	public List<User> search(String username) {
		List<LdapUser> list = wsClient.search(username.toUpperCase(), null, null);
		List<User> results = new ArrayList<>();
		for (LdapUser ldapUser : list) {
			results.add(toUser(ldapUser));
		}
		return results;
	}

	private User toUser(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}
}