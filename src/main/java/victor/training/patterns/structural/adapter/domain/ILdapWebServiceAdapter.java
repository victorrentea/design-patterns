package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface ILdapWebServiceAdapter {
   List<User> searchByUsername(String username);
}
