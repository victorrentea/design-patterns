package victor.training.oo.structural.adapter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LdapServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   private User convertUser(LdapUser ldapUser) {
      String fullName = getFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   private String getFullName(LdapUser ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null).stream()
         .map(this::convertUser)
         .collect(Collectors.toList());
   }
}
