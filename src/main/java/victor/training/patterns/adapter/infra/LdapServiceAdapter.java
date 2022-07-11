package victor.training.patterns.adapter.infra;

import org.springframework.stereotype.Component;
import victor.training.patterns.adapter.domain.ILdapServiceAdapter;
import victor.training.patterns.adapter.domain.User;

import java.util.List;

@Component
public class LdapServiceAdapter implements ILdapServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    LdapServiceAdapter(LdapUserWebserviceClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public User retrieveUserByUsername(String username) {

        List<LdapUserDto> list = wsClient.search(username.toUpperCase(), null, null);
        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }
        LdapUserDto ldapUser = list.get(0);
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

//    public LdapUserDto neatent() {
//        // gresit!!!
//    }
}
