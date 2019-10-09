package victor.training.oo.structural.adapter.external;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapUserServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LdapUserServiceAdapter implements ILdapUserServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    @Override
    public List<User> searchUserByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream()
                .map(this::convertToUser)
                .collect(toList());
    }

    private User convertToUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

}
