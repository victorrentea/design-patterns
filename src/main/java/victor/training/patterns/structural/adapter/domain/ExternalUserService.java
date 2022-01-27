package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface ExternalUserService {
   List<User> searchByUsername(String username);
}
