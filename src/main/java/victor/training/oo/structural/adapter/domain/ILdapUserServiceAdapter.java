package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapUserServiceAdapter {

	List<User> searchByUsername(String username);

}