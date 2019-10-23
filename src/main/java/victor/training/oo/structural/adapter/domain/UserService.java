package victor.training.oo.structural.adapter.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService { // SACRED GROUND ZEN PEACE SACRED GROUND ZEN PEACE SACRED GROUND ZEN PEACE SACRED GROUND ZEN PEACE SACRED GROUND ZEN PEACE

	private final UserRepository userRepo;

	public void importUserFromLdap(String username) {
		List<User> list = userRepo.searchByUsername(username);
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
		return userRepo.searchByUsername(username);
	}
}
