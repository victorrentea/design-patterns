package victor.training.oo.structural.adapter.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.IUserServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

// -----------------------------------------------------------
// garbage; laundry stuff;
@Service
public class UserServiceAdapter implements IUserServiceAdapter {
    @Autowired
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> findUsersByUsername(String username) {
        List<LdapUser> ldapUsers = wsClient.search(username.toUpperCase(), null, null);
        return ldapUsers.stream().map(this::createUser).collect(Collectors.toList());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    private User createUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
