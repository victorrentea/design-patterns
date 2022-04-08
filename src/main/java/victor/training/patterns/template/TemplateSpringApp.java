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
      // more logic
      new OrderPlacedEmailSender().sendEmail("a@b.com");
   }

   private void shipOrder() {
      // more logic
      // TODO implement 'similar to how order placed email was implemented'
      new OrderShippedEmailSender().sendEmail("a@b.com");
   }
}

abstract class EmailSender {
   public void sendEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            customizeEmail(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

   protected abstract void customizeEmail(Email email);

   public String encodeSubject(String s) {
      return s.toUpperCase();
   }
}

class OrderPlacedEmailSender extends EmailSender {
   protected void customizeEmail(Email email) {
      email.setSubject(encodeSubject("Order Placed"));
      email.setBody("Thank you for your order");
   }
}

class OrderShippedEmailSender extends EmailSender {
   protected void customizeEmail(Email email) {
      email.setSubject(encodeSubject("Order Shipped"));
      email.setBody("Ti-am trimas!");
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