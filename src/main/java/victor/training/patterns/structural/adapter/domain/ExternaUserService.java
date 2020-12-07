package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface ExternaUserService {
   List<User> searchByUsername(String username);
}
