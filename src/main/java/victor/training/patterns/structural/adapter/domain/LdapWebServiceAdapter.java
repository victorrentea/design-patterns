package victor.training.patterns.structural.adapter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.infra.LdapUserDto;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class LdapWebServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convert)
          .collect(toList());
   }

   private User convert(LdapUserDto ldapUser) {
      String fullName = ldapUser.getFirstName() + " " + ldapUser.getLastName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   // TODO @end: Archunit!
}
