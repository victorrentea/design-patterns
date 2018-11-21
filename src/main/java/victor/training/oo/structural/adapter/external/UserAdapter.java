package victor.training.oo.structural.adapter.external;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.oo.structural.adapter.domain.IUserAdapter;
import victor.training.oo.structural.adapter.domain.User;

@Service
public class UserAdapter implements IUserAdapter {

	@Autowired
	private LdapUserWebserviceClient wsClient;

	@Override
	public List<User> search(String username) {
		List<LdapUser> list = wsClient.search(username.toUpperCase(), null, null);
		List<User> results = new ArrayList<>();
		for (LdapUser ldapUser : list) {
			String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
			User user = new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
			results.add(user);
		}
		return results;
	}
}
