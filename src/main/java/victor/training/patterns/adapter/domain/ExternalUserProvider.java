package victor.training.patterns.adapter.domain;

import victor.training.patterns.adapter.domain.User;

public interface ExternalUserProvider {
    User fetchUser(String username);
}
