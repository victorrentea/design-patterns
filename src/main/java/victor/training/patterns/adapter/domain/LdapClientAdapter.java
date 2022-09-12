package victor.training.patterns.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.adapter.infra.LdapUserDto;
import victor.training.patterns.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LdapClientAdapter {
    private final LdapUserWebserviceClient wsClient;

    public User findByUsername(String username) {
        List<LdapUserDto> list = wsClient.search(username.toUpperCase(), null, null);
        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }
        LdapUserDto ldapUser = list.get(0);
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        User user = new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
        return user;
    }
}
