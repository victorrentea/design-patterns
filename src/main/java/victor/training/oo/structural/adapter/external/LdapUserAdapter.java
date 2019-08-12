package victor.training.oo.structural.adapter.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapUserAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

/// sectiunea 2
@Service
public class LdapUserAdapter implements ILdapUserAdapter {
    @Autowired
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> stefanMethod(String username) {
        List<LdapUser> ldapUsers = searchInLdap(username);
        List<User> results = new ArrayList<>();
        for (LdapUser ldapUser : ldapUsers) {
            User user = convertUser(ldapUser);
            results.add(user);
        }
        return results;
    }

    private List<LdapUser> searchInLdap(String username) {
        return wsClient.search(username.toUpperCase(), null, null);
    }

    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
