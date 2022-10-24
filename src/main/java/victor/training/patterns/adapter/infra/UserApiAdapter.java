package victor.training.patterns.adapter.infra;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.adapter.domain.Adapter;
import victor.training.patterns.adapter.domain.IUserApiAdapter;
import victor.training.patterns.adapter.domain.User;

import java.util.List;


@RequiredArgsConstructor
@Adapter
public class UserApiAdapter implements IUserApiAdapter {
    private final LdapUserApiClient apiClient;

    @Override
    public User fetchUser(String username) {
        List<LdapUserDto> list = apiClient.search(null, null, username.toUpperCase());

        if (list.size() != 1) {
            throw new IllegalArgumentException("There is  MARIO no single user matching username " + username);
        }

        LdapUserDto ldapUser = list.get(0);
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        User user = new User(ldapUser.getUsername(), ldapUser.getWorkEmail(), fullName);
        return user;
    }

}
