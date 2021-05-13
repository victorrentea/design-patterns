package victor.training.patterns.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.domain.ExternalServiceAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LdapServiceAdapter implements ExternalServiceAdapter {
   @Autowired
   private LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(ldapUser -> convert(ldapUser)).collect(Collectors.toList());
   }

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

}