package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapServiceAdapter {

	// Holy Domain Logic. 
	// Very precious things that I want to keep agnostic to technical details
	List<User> search(String username);

}