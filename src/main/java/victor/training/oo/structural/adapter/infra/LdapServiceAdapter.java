package victor.training.oo.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LdapServiceAdapter implements ILdapServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .filter(Objects::nonNull)
          .map(this::convertUser)
          .collect(Collectors.toList());
   }

   private User convertUser(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

}
