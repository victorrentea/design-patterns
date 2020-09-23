package victor.training.oo.behavioral.template;

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
      // other logic
      new EmailSender(Emails::composeEmailReceived).sendEmail("a@b.com");
   }

   private void shipOrder() {
      EmailComposer c = email -> System.out.println("Orice");
      // other logic
      // TODO send order shipped email 'similar to how send "order received" was implemented'
      new EmailSender(Emails::composeEmailShipped).sendEmail("a@b.com");
   }
}

interface EmailComposer {
   void compose(Email email);
}
class Emails {
   public static void composeEmailReceived(Email email) {
      email.setSubject("Order Received");
      email.setBody("Thank you for your order");
   }
   public static void composeEmailShipped(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("Ti-am trimis. Speram sa ajunga (de data asta).");
   }
}
class EmailSender {

   private final EmailComposer composer;

   public EmailSender(EmailComposer composer) {
      this.composer = composer;
   }
   public void sendEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      for (int i = 0; i < MAX_RETRIES; i++) {
         Email email = new Email(); // constructor generates new unique ID
         email.setSender("noreply@corp.com");
         email.setReplyTo("/dev/null");
         email.setTo(emailAddress);
         composer.compose(email);
         boolean success = context.send(email);
         if (success) break;
      }
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
   private String subject;
   private String body;
   private final long id = new Random(System.nanoTime()).nextLong();
   private String sender;
   private String replyTo;
   private String to;
}