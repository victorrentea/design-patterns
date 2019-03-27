package victor.training.oo.structural.adapter.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.IUserWSAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWSAdapter implements IUserWSAdapter {

    @Autowired
    private LdapUserWebserviceClient wsClient;

    @Override
    public List<User> search(String username) {
        List<LdapUser> list = doSearch(username);
        List<User> results = new ArrayList<>();
        for (LdapUser ldapUser : list) {
            User user = mapToUser(ldapUser);
            results.add(user);
        }
        return results;
    }

    private List<LdapUser> doSearch(String username) {
        return wsClient.search(username.toUpperCase(), null, null);
    }

    private User mapToUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
