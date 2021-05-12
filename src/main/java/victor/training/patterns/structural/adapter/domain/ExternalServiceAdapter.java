package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface ExternalServiceAdapter {
   List<User> searchByUsername(String username);
}
