package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IUserWSAdapter {
    List<User> search(String username);
}
