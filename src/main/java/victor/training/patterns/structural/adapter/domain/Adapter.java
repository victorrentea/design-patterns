package victor.training.patterns.structural.adapter.domain;

import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Adapter {
   private final LdapUserWebserviceClient wsClient;

   public Adapter(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::convert).collect(toList());
   }
}
