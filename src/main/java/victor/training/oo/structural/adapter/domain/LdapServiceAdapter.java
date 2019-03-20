package victor.training.oo.structural.adapter.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

@Service
public class LdapServiceAdapter {  
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