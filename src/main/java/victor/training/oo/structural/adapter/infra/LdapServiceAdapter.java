package victor.training.oo.structural.adapter.infra;

import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
// TU cel ce intri, abandoneaza orice speranta
public class LdapServiceAdapter implements ILdapServiceAdapter {
    private final LdapUserWebserviceClient wsClient;
    public LdapServiceAdapter(LdapUserWebserviceClient wsClient) {
        this.wsClient = wsClient;
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream()
                .map(this::convertUser)
                .collect(toList());
    }
}
