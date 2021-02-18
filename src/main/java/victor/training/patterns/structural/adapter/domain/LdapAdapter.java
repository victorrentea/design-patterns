package victor.training.patterns.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class LdapAdapter {
   private final LdapUserWebserviceClient wsClient;

   private User convertToEntity(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convertToEntity)
          .collect(toList());
   }
}
