package victor.training.oo.structural.adapter.external;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.oo.structural.adapter.domain.IUserWSAdapter;
import victor.training.oo.structural.adapter.domain.User;

@Service
public class UserWSAdapter implements IUserWSAdapter {
	@Autowired
	private LdapUserWebserviceClient wsClient;
	
	/* (non-Javadoc)
	 * @see victor.training.oo.structural.adapter.external.IUserWSAdapter#findByUsername(java.lang.String)
	 */
	public List<User> findByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
			.map(this::toEntity)
			.collect(toList());
	}
	
	private User toEntity(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
	}
	
}
