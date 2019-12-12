package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

// dining room
// a line ---------------------------------------
// laundry room
@RequiredArgsConstructor
@Service
public class LdapAdapter {
    private final LdapUserWebserviceClient wsClient;
    // You that enters here, abandon all hope.

    private User convertFrom(LdapUser ldapUser) {
        return new User(ldapUser.getuId(), mapFullName(ldapUser), ldapUser.getWorkEmail());
    }

    private String mapFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::convertFrom).collect(toList());
    }
}
