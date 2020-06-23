package victor.training.oo.structural.adapter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

//  ------------------- o linie ----~~~~~~~~~===========
//tu cel ce intri, abandoneaza orice speranta si mandrie
@Component
public class LdapUserWebserviceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convertUser)
          .collect(Collectors.toList());
   }

   private User convertUser(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

}
