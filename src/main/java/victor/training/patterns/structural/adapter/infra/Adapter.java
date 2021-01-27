package victor.training.patterns.structural.adapter.infra;

import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.User;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Adapter implements victor.training.patterns.structural.adapter.domain.IAdapter {
   private final LdapUserWebserviceClient wsClient;

   public Adapter(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::convert).collect(toList());
   }
}
