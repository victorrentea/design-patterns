package victor.training.patterns.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.ExternalUserService;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UserLdapAdapter implements ExternalUserService {
   @Autowired
   public LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::fromDto)
          .collect(toList());
   }

   private User fromDto(LdapUserDto ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      // my data structure, in MY DOMAIN!
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }
}