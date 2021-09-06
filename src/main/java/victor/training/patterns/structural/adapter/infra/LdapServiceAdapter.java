package victor.training.patterns.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.ExternalUserService;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LdapServiceAdapter implements ExternalUserService {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::fromDto)
          .collect(Collectors.toList());
   }

   public LdapUserDto mistake(String username) {
      return null;// DOTO
   }

   private String getFullName(LdapUserDto ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   private User fromDto(LdapUserDto ldapUser) {
      String fullName = getFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }


}
