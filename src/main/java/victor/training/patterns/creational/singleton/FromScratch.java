package victor.training.patterns.creational.singleton;

import org.springframework.stereotype.Service;

//@Service
//@Component
//@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Singleton
//@Stateless
public class FromScratch {
   // NEVER WRITE THIS in 2022 >
//   private static FromScratch INSTANCE;
//   private FromScratch() {}
//   public static FromScratch getInstance() {
//      if (INSTANCE ==null) {
//         INSTANCE = new FromScratch();
//      }
//      return INSTANCE;
//   }

   // one access to DB, one resource per app DBConnectionPool(20)
//   private final Dep dep;

   private String currentUsername; // BAD VERY BAD mutable state in a multi-threaded web environment

   public void setCurrentUsername(String currentUsername) {
      this.currentUsername = currentUsername;
   }

   public void bizLogic() {
      System.out.println(currentUsername);
   }
}

@Service
class Client {
//   @Autowired
//   private FromScratch fromScratch;

   public void webEndpoint() {
      FromScratch fromScratch = new FromScratch();
      fromScratch.setCurrentUsername("jdoe");
      fromScratch.bizLogic();
   }
}

/// WHY singleton