package victor.training.patterns.structural.adapter.domain;

import java.util.List;

public interface IAdapter {
   List<User> searchByUsername(String username);
}
