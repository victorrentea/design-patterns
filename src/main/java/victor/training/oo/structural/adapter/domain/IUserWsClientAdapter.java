package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IUserWsClientAdapter {

	List<User> searchByUsername(String username);

}