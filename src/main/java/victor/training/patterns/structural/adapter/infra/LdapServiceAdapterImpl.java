package victor.training.patterns.structural.adapter.infra;

import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.LdapServiceAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LdapServiceAdapterImpl implements LdapServiceAdapter {
   private final LdapUserWebserviceClient wsClient;

   LdapServiceAdapterImpl(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   @Override
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
