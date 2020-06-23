package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapUserWebserviceAdapter {
   List<User> searchByUsername(String username);
}
