package victor.training.oo.structural.adapter.external;

import lombok.AllArgsConstructor;
import victor.training.oo.structural.adapter.domain.IUserRepository;
import victor.training.oo.structural.adapter.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserRepository implements IUserRepository {
    private final LdapUserWebserviceClient wsClient;

    public List<User> search(String username) {
        List<LdapUser> list = wsClient.search(username.toUpperCase(), null, null);
        return list.stream().map(this::convertUser).collect(Collectors.toList());
    }


    private User convertUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
