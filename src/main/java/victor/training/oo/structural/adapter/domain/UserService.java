package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
// Suntem in cel mai sacru loc din app noastra: in Domain Service, Unde ar trebui sa fie cea mai pura si frumoasa logica posibila
public class UserService {
   private final ExternalUserService ldapUserServiceAdapter;

   // viata-i grea, n-o poate toti
   public void importUserFromLdap(String username) {
      List<User> list = ldapUserServiceAdapter.searchByUsername(username);
      if (list.size() != 1) {
         throw new IllegalArgumentException("There is no single user matching username " + username);
      }
      User user = list.get(0);

      if (user.getWorkEmail() != null) {
         log.debug("Send welcome email to " + user.getWorkEmail());
      }
      log.debug("Insert user in my database");
   }

   public List<User> searchUserInLdap(String username) {
      return ldapUserServiceAdapter.searchByUsername(username);
   }
   // paradisul



}
