package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
   }

   @Autowired
   private EmailSender emailSender;


   private void placeOrder() {
      // more logic
      emailSender.sendEmail("a@b.com", new OrderPlacedEmailComposer());
   }

   private void shipOrder() {
      // more logic
      // TODO implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode
      emailSender.sendEmail("a@b.com", new OrderShippedEmailComposer());
   }
}
@Service
class EmailSender {

   public void sendEmail(String emailAddress, EmailComposer composer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            composer.compose(email);
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
}
class OrderPlacedEmailComposer implements  EmailComposer {
   public void compose(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }
}

class OrderShippedEmailComposer implements  EmailComposer {
   public void compose(Email email) {  // NEVER DO THAT: override concrete method > creates confusion and panic
      email.setSubject("Order Shipped!");
      email.setBody("We've shipped the order to you");
      email.encrypt();
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

   public void setAttachment(String attach) {

   }

   public void encrypt() {

   }
}