package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IUserRepository {

	List<User> search(String username);

}