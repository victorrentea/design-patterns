package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
   public static void main(String[] args) {
      SpringApplication.run(TemplateSpringApp.class, args);
   }

   public void run(String... args) {
      placeOrder();
      shipOrder();
//      new BufferedWriter(new FileWriter());
//      new JdbcTemplate().query("", (rs, rowNum) -> null)
   }

   @Autowired
   EmailSender emailSender;
   @Autowired
   AllEmails allEmails;

   private void placeOrder() {
      // more logic
      emailSender.sendEmail("a@b.com", allEmails::composeOrderPlacedEmail);
   }

   private void shipOrder() {
//      List<Integer> list = List.of(1, 2);
//      list.stream()
      emailSender.sendEmail("a@b.com", allEmails::composeOrderShippedEmail);
      // more logic
      // TODO implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode

   }
//   WebMvcConfigurer
}

//interface Topmost{}
//abstract class CommonBase implements Topmost{}
//class Imp1 extends CommonBase implements Topmost {commonDep}
//class Imp2 extends CommonBase implements Topmost {commonDep}
//class Imp3 extends CommonBase implements Topmost {commonDep}
//class Imp4Black implements Topmost {}
// only use default methods if you want to ADD an extra convenience method to an interface implemented by unknown developers. NEVER>
//class MyImpl1 implements EmailSender extends BaseImpl {}
@Service
class EmailSender {

   public void sendEmail(String emailAddress, EmailComposer emailComposer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo("a@b.com");
            emailComposer.compose(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

}

@Component
class AllEmails {
   public void composeOrderPlacedEmail(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
//    email.addAttachment(invoice)
   }

   public void composeOrderShippedEmail(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("We shipped you. Hope it gets to you this time");
   }
}

interface EmailComposer {
   void compose(Email email);
}

class EmailContext {
   public boolean send(Email email) {
      System.out.println("Trying to send " + email);
      return new Random(System.nanoTime()).nextBoolean();
   }
}

@Data
class Email {
   private final long id = new Random(System.nanoTime()).nextLong();
   private String subject;
   private String body;
   private String sender;
   private String replyTo;
   private String to;
}