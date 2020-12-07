package victor.training.patterns.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.infra.LdapUser;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LdapUserServiceAdapter {
   private final LdapUserWebserviceClient wsClient;

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convertToEntity)
          .collect(toList());
   }

   private User convertToEntity(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

}
