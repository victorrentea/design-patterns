package victor.training.oo.structural.adapter.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Data

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	private final ILdapUserServiceAdapter userServiceAdapter;
	
	public void importUserFromLdap(String username) {
		List<User> list = userServiceAdapter.searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);
		
		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}
	
	public List<User> searchUserInLdap(String username) {
		return userServiceAdapter.searchByUsername(username);
	}
}

