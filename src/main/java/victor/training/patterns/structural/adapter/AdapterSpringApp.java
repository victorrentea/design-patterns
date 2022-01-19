package victor.training.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import victor.training.patterns.structural.adapter.domain.UserService;

@SpringBootApplication
public class AdapterSpringApp implements CommandLineRunner {
   private static final Logger log = LoggerFactory.getLogger(AdapterSpringApp.class);
   @Autowired
   private UserService userService;

   public static void main(String[] args) {
      SpringApplication.run(AdapterSpringApp.class, args);
   }

   public void run(String... args) throws Exception {
      userService.importUserFromLdap("jdoe");
      log.debug("Found users: " + userService.searchUserInLdap("doe"));
   }
}