package victor.training.patterns.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import victor.training.patterns.structural.adapter.domain.IAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Adapter implements IAdapter {// Domain Logic
   @Autowired
   private LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::fromDto)
          .collect(Collectors.toList());
   }

   private User fromDto(LdapUserDto ldapUser) {
      String corporateName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), corporateName, ldapUser.getWorkEmail());
   }
}