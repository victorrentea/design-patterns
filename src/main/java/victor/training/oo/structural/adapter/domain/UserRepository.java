package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface UserRepository {
    List<User> searchByUsername(String username);
}
