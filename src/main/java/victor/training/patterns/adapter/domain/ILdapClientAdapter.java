package victor.training.patterns.adapter.domain;

import victor.training.patterns.adapter.domain.User;

public interface ILdapClientAdapter {
    User findByUsername(String username);
}
