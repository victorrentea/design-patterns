package victor.training.patterns.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.adapter.domain.ILdapClientAdapter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LdapClientAdapter implements ILdapClientAdapter {
    private final LdapUserWebserviceClient wsClient;

    @Override
    public LdapUserDto findByUsername(String username) {
        List<LdapUserDto> list = wsClient.search(username.toUpperCase(), null, null);
        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }
        LdapUserDto ldapUser = list.get(0);
        return ldapUser;
    }
}
