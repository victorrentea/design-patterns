package victor.training.patterns.structural.adapter.domain;

import victor.training.patterns.structural.adapter.infra.LdapUserDto;
import victor.training.patterns.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

public class ExternalUserService {
   private final LdapUserWebserviceClient wsClient;

   ExternalUserService(LdapUserWebserviceClient wsClient) {
      this.wsClient = wsClient;
   }

   public List<User> searchByUsername(String username) {
      List<LdapUserDto> dtos = wsClient.search(username.toUpperCase(), null, null, null);
      List<User> users = new ArrayList<>();
      for (LdapUserDto dto : dtos) {
         users.add(fromDto(dto));
      }
      return users;
   }

   private User fromDto(LdapUserDto ldapUser) {
      String fullName = toCorporateName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   private String toCorporateName(LdapUserDto ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

}
