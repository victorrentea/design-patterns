package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//abstract class AbstC {
//   int x= 2;
//    int sum(int b, int a) {
//      return a + b + ++ x;
//   }
//}
//interface I2 {
//   int x= 2;
//   default int sum(int b, int a) {
//      return a + b + ++x;
//   }
//}
//class P implements I2 {
//
//}

@Slf4j
@Service
// DOMENIUL MEU. AL MEU. My Preciousssss!
public class UserService {
   @Autowired
   private ExternalUserService adapter;

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
