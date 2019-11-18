package victor.training.oo.structural.adapter.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.UserProvider;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LdapUserProvider implements UserProvider {
    private final LdapUserWebserviceClient wsClient;

    private User convertUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream().map(this::convertUser).collect(Collectors.toList());
    }
}
