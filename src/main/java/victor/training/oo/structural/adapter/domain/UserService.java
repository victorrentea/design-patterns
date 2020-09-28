package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
// DOMENIUL MEU. AL MEU. My Preciousssss!
public class UserService {
   @Autowired
   private LdapServiceAdapter adapter;

   public void importUserFromLdap(String username) {
      List<User> list = adapter.searchByUsername(username);
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
      return adapter.searchByUsername(username);
   }

   // ce bun
   // o linie -----------------------------------------------------------------------------------------------------
   // ce e rau
   // Tu cel ce intri, abandoneaza orice speranta.

}
