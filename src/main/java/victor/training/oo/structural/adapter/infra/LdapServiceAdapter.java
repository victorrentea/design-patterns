package victor.training.oo.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LdapServiceAdapter implements ILdapServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   private User convertUser(LdapUser ldapUser) {
      String fullName = getFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   private String getFullName(LdapUser ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null).stream()
         .map(this::convertUser)
         .collect(Collectors.toList());
   }
}
