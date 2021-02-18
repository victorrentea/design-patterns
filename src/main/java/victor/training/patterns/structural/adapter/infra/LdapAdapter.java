package victor.training.patterns.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.ILdapAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class LdapAdapter implements ILdapAdapter {
   private final LdapUserWebserviceClient wsClient;

   private User convertToEntity(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convertToEntity)
          .collect(toList());
   }
}
