package victor.training.patterns.structural.adapter.infra;

import victor.training.patterns.structural.adapter.domain.IAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter implements IAdapter {
   private final LdapUserWebserviceClient wsClient;

   public Adapter(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   private User convertUser(LdapUser ldapUser) {
      String fullName = getFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   private String getFullName(LdapUser ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null, null)
          .stream()
          .map(this::convertUser)
          .collect(Collectors.toList());
   }
}
