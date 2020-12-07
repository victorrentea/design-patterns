package victor.training.patterns.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.domain.ExternaUserService;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class  LdapUserServiceAdapter implements ExternaUserService {
   private final LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream()
          .map(this::convertToEntity)
          .collect(toList());
   }

   private User convertToEntity(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

}
