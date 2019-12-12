package victor.training.oo.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

// dining room
// a line ---------------------------------------
// laundry room
@RequiredArgsConstructor
@Service
public class LdapAdapter implements ILdapAdapter {
    private final LdapUserWebserviceClient wsClient;
    // You that enters here, abandon all hope.

    private User convertFrom(LdapUser ldapUser) {
        return new User(ldapUser.getuId(), mapFullName(ldapUser), ldapUser.getWorkEmail());
    }

    private String mapFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::convertFrom).collect(toList());
    }
}
