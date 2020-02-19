package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ExternalUserProvider {

	List<User> findUsersByUsername(String username);

}