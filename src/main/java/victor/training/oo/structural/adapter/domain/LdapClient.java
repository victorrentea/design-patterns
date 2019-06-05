package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface LdapClient {
    List<User> searchUser(String username);
}
