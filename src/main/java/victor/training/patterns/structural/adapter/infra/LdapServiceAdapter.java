package victor.training.patterns.structural.adapter.infra;

import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

// al meu (sufragerie)
// ----------------------------------------------------------------
// al lor (buda)
public class LdapServiceAdapter implements victor.training.patterns.structural.adapter.domain.ILdapServiceAdapter {
   private final LdapUserWebserviceClient wsClient;

   LdapServiceAdapter(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   private User translateUser(LdapUser ldapUser) {
      String corporateName = formatCorporateName(ldapUser);
      return new User(ldapUser.getuId(), corporateName, ldapUser.getWorkEmail());
   }

   private String formatCorporateName(LdapUser ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   @Override
   public List<User> searchByUsername(String username) {
      List<LdapUser> ldapUsers = wsClient.search(username.toUpperCase(), null, null);
      return ldapUsers.stream().map(this::translateUser).collect(toList());
   }

// TODO @end: Archunit!
}
