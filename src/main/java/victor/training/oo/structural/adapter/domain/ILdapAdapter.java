package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapAdapter {
    List<User> searchByUsername(String username);
}
