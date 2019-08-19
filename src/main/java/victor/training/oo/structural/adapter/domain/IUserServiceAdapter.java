package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IUserServiceAdapter {
    List<User> findUsersByUsername(String username);
}
