package victor.training.patterns.template;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
   public static void main(String[] args) {
      SpringApplication.run(TemplateSpringApp.class, args);
   }

   public void run(String... args) {
      placeOrder();
      shipOrder();
   }

   private void placeOrder() {
      // logic
      new EmailSender(new OrderPlacedEmailComposer()).sendEmail("a@b.com");
   }

   private void shipOrder() {
      // logic
      new EmailSender(new OrderShippedEmailComposer()).sendEmail("a@b.com");
      // TODO implement 'similar to how order placed email was implemented'
   }
}

 class EmailSender {
   private final EmailComposer emailComposer;

    EmailSender(EmailComposer emailComposer) {
       this.emailComposer = emailComposer;
    }

    public void sendEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 3; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            emailComposer.compose(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

}
interface EmailComposer {
   void compose(Email email);
//   private String encodeSubject(String s) {
//      return s.toUpperCase();
//   }
}
// dep on the country, the zip number is different
class OrderPlacedEmailComposer implements EmailComposer {
   public void compose(Email email) {
      email.setSubject("Order Placed");
      email.setBody("Thank you for your order");
   }
}
class OrderShippedEmailComposer implements EmailComposer {
   public void compose(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("Thank you for your order");
//      email.addAttachment(pdf)
   }
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