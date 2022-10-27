package victor.training.patterns.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.adapter.domain.ExternalUserProvider;
import victor.training.patterns.adapter.domain.User;

import java.util.List;

import static java.util.Optional.ofNullable;
@Component
@interface Adapter {}

@Adapter
public class LdapUserClientAdapter implements ExternalUserProvider {
    @Autowired
    private LdapUserApiClient apiClient;
    @Override
    public User fetchUser(String username) {
        List<LdapUserDto> list = apiClient.search(username.toUpperCase(), null, null);

        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }

        LdapUserDto ldapUser = list.get(0);
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase(); // mapping spread in my domain

        User user = new User(ldapUser.getuId(),
                fullName, ofNullable(ldapUser.getWorkEmail()));
        return user;
    }
}
