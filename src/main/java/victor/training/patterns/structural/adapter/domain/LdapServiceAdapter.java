package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface LdapServiceAdapter {
   List<User> searchByUsername(String username);
}
