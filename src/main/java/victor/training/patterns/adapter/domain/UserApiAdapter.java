package victor.training.patterns.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.adapter.infra.LdapUserApiClient;
import victor.training.patterns.adapter.infra.LdapUserDto;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;


@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Adapter {
}

@RequiredArgsConstructor
@Adapter
public class UserApiAdapter {
    private final LdapUserApiClient apiClient;

    public User fetchUser(String username) {
        List<LdapUserDto> list = apiClient.search(null, null, username.toUpperCase());

        if (list.size() != 1) {
            throw new IllegalArgumentException("There is  MARIO no single user matching username " + username);
        }

        LdapUserDto ldapUser = list.get(0);
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        User user = new User(ldapUser.getuId(), ldapUser.getWorkEmail(), fullName);
        return user;
    }

}
