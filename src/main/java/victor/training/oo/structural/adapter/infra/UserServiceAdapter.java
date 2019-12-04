package victor.training.oo.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.IUserServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
 public class UserServiceAdapter implements IUserServiceAdapter {
    @Autowired
    private LdapUserWebserviceClient wsClient;
    private User mapUserFromLdap(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::mapUserFromLdap)
                .collect(Collectors.toList());
    }
}
