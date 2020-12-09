package victor.training.patterns.structural.adapter.infra;

import victor.training.patterns.structural.adapter.domain.IAdapter;
import victor.training.patterns.structural.adapter.domain.UserService;

public class Main {
   public static void main(String[] args) {

      LdapUserWebserviceClient ws = new LdapUserWebserviceClient();
      IAdapter adapter = new Adapter(ws);
      UserService userService = new UserService(adapter);
      userService.importUserFromLdap("jdoe");
   }
}
