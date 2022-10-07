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
      new OrderPlacedEmailSender().sendOrderPlacedEmail("a@b.com");
   }

   private void shipOrder() {
      // logic
      new OrderShippedEmailSender().sendOrderPlacedEmail("a@b.com");
      // TODO implement 'similar to how order placed email was implemented'
   }
}

abstract class EmailSender {

   public void sendOrderPlacedEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 3; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            fillEmail(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

   protected abstract void fillEmail(Email email);
}
// dep on the country, the zip number is different
class OrderPlacedEmailSender extends EmailSender {

   protected void fillEmail(Email email) {
      email.setSubject("Order Placed");
      email.setBody("Thank you for your order");
   }
}
class OrderShippedEmailSender extends EmailSender {
   protected void fillEmail(Email email) {
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