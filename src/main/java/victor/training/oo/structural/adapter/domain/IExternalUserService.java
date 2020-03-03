package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IExternalUserService {
    List<User> searchByUsername(String username);
}
