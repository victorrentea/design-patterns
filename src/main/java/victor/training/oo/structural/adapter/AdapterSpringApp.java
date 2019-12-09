package victor.training.oo.structural.adapter;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.domain.UserService;
import victor.training.oo.structural.adapter.domain.UserServiceAdapter;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

@Slf4j
public class AdapterSpringApp {
	public static void main(String[] args) {
		LdapUserWebserviceClient wsClient = new LdapUserWebserviceClient();
		UserServiceAdapter adapter = new UserServiceAdapter(wsClient);
		UserService userService = new UserService(adapter);
		userService.importUserFromLdap("jdoe");
		log.debug("Found users: " + userService.searchUserInLdap("doe"));
	}
}