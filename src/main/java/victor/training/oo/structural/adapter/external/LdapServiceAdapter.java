package victor.training.oo.structural.adapter.external;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class LdapServiceAdapter implements ILdapServiceAdapter {
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> unSearchMaiBun(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::convertUser)
                .collect(Collectors.toList());
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
