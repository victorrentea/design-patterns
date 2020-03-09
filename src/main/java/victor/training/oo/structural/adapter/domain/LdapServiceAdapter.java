package victor.training.oo.structural.adapter.domain;

import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
// TU cel ce intri, abandoneaza orice speranta
public class LdapServiceAdapter {
    private final LdapUserWebserviceClient wsClient;
    public LdapServiceAdapter(LdapUserWebserviceClient wsClient) {
        this.wsClient = wsClient;
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream()
                .map(this::convertUser)
                .collect(toList());
    }
}
