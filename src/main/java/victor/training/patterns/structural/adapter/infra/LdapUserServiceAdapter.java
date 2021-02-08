package victor.training.patterns.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.domain.ExternalUserService;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LdapUserServiceAdapter implements ExternalUserService {
   private final LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      try {
         return wsClient.search(username.toUpperCase(), null, null)
             .stream().map(this::toEntity).collect(Collectors.toList());
      } catch (Exception e) {
         throw new RuntimeException("It's their fault !!", e);
      }
   }

   private User toEntity(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   // TODO @end: Archunit!
}
