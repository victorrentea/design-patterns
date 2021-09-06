package victor.training.patterns.structural.adapter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.infra.LdapUserDto;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LdapServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::fromDto)
          .collect(Collectors.toList());
   }

   private String getFullName(LdapUserDto ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   private User fromDto(LdapUserDto ldapUser) {
      String fullName = getFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }


}
