package victor.training.patterns.structural.adapter.domain;

import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LdapServiceAdapter {
   private final LdapUserWebserviceClient wsClient;

   LdapServiceAdapter(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::convert).collect(Collectors.toList());
   }

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   // TODO @end: Archunit!
}
