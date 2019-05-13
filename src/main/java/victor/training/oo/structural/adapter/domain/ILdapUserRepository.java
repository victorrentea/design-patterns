package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapUserRepository {

	List<User> search(String username);

}