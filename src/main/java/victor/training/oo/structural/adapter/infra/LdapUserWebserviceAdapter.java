package victor.training.oo.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.domain.ILdapUserWebserviceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

//  ------------------- o linie ----~~~~~~~~~===========
//tu cel ce intri, abandoneaza orice speranta si mandrie
//@Component
////@Profile({"user-from-db", "redis"}) == ar trebui sa mearga daca AMBELE profile sunt activate
//class DBUserWebserviceAdapter {
//
//}
@Component
//@Profile("user-from-ldap")
public class LdapUserWebserviceAdapter implements ILdapUserWebserviceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   @Override
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
