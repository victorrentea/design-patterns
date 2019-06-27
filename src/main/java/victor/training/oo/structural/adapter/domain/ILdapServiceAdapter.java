package victor.training.oo.structural.adapter.domain;

import victor.training.oo.structural.adapter.domain.User;

import java.util.List;

public interface ILdapServiceAdapter {
    List<User> search(String username);
}
