package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Consumer;

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
   @Autowired
   private Emails emails;

   private void placeOrder() {
      // more logic
      emailSender.sendEmail("a@b.com", emails::composeOrderPlaced);
   }

   private void shipOrder() {
      // more logic
      // TODO implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode
      // unit test
      emailSender.sendEmail("a@b.com", emails::composeOrderShipped);
   }
}

@Service
class EmailSender {

   // infra
   public void sendEmail(String emailAddress, Consumer<Email> composer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            composer.accept(email);

            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }
}

@Component
class Emails {
   public void composeOrderPlaced(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
      // complex
   }

   public void composeOrderShipped(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("We shipped it ");
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