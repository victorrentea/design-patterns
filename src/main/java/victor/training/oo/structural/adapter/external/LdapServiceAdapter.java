package victor.training.oo.structural.adapter.external;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LdapServiceAdapter implements ILdapServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    @Override
    public List<User> search(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }

    private User mapUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }
}
