package victor.training.patterns.structural.adapter.domain;

import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

public interface IAdapter {
   List<User> searchByUsername(String username);
}
