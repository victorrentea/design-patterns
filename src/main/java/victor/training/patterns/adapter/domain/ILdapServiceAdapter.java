package victor.training.patterns.adapter.domain;

import victor.training.patterns.adapter.domain.User;

public interface ILdapServiceAdapter {
    User retrieveUserByUsername(String username);
}
