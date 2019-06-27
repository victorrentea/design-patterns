package victor.training.oo.structural.adapter.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public
class LdapServiceAdapter implements ILdapServiceAdapter {
    @Autowired
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> search(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::convertUser)
                .collect(Collectors.toList());
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

}
