package victor.training.patterns.structural.adapter.infra;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import victor.training.patterns.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.patterns.structural.adapter.domain.User;

@Component
public class LdapServiceAdapter implements ILdapServiceAdapter {
	@Autowired
	private LdapUserWebserviceClient wsClient;

	@Override
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