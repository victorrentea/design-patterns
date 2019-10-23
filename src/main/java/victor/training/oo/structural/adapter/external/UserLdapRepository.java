package victor.training.oo.structural.adapter.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.domain.UserRepository;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

// you, that enters here, abanon all hope -------------------------- ZOMBIES BELOW
@Service
@RequiredArgsConstructor
public class UserLdapRepository implements UserRepository {
    private final LdapUserWebserviceClient wsClient;

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream()
                .map(this::convertUser)
                .collect(toList());
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }
}
