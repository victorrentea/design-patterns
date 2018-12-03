package victor.training.oo.structural.adapter.external;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import victor.training.oo.structural.adapter.domain.ILdapUserServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

@Service
public class LdapUserServiceAdapter implements ILdapUserServiceAdapter {
	@Autowired
	private LdapUserWebserviceClient wsClient;

	/* (non-Javadoc)
	 * @see victor.training.oo.structural.adapter.external.ILdapUserServiceAdapter#searchByUsername(java.lang.String)
	 */
	public List<User> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null).stream()
				.map(this::toUser)
				.collect(toList());
	}
	
	private User toUser(LdapUser ldapUser) {
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		if (StringUtils.isBlank(ldapUser.getuId())) {
			throw new IllegalArgumentException("uid");
		}
		User user = new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
		return user;
	}
	
}