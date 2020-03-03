package victor.training.oo.structural.adapter.infra;

import victor.training.oo.structural.adapter.domain.IExternalUserService;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExternalUserService implements IExternalUserService {
    private LdapUserWebserviceClient wsClient;
    private User buildUser(LdapUser ldapUser) {
        String fullName = extractFullName(ldapUser);
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    private String extractFullName(LdapUser ldapUser) {
        return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
    }

    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null).stream()
                .map(this::buildUser)
                .collect(toList());
    }
}
