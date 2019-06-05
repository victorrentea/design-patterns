package victor.training.oo.structural.adapter.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.LdapClient;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LdapClientAdapter implements LdapClient {
    @Autowired
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> searchUser(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::toUser)
                .collect(Collectors.toList());
    }

    private User toUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
