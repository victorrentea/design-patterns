package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface ExternalUserProvider {
   List<User> searchByUsername(String username);
}
