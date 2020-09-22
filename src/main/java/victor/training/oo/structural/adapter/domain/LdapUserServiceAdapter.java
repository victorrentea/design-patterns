package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface LdapUserServiceAdapter {
   List<User> searchByUsername(String username);
}
