package victor.training.oo.structural.adapter.infra;

import victor.training.oo.structural.adapter.domain.IUserServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.ArrayList;
import java.util.List;

// o linie -----------------------------------------------
//gunoi.
public class UserServiceAdapter implements IUserServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    public UserServiceAdapter(LdapUserWebserviceClient wsClient) {
        this.wsClient = wsClient;
    }

    private User transformUser(LdapUser ldapUser) {
        String fullName = getFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String getFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    @Override
    public List<User> searchByUsername(String username) {
        List<LdapUser> ldapUsers = wsClient.search(username.toUpperCase(), null, null);
        List<User> users = new ArrayList<>();
        for (LdapUser ldapUser : ldapUsers) {
            User user = transformUser(ldapUser);
            users.add(user);
        }
        return users;
    }
}
