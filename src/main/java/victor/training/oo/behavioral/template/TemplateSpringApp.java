package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.Writer;
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
   EmailSender sender;
   private void placeOrder() {
      // other logic
      sender.sendEmail("a@b.com",Emails::composeEmailReceived);
   }

   private void shipOrder() {
//      EmailComposer c = email -> System.out.println("Orice");
      // other logic
      // TODO send order shipped email 'similar to how send "order received" was implemented'
      sender.sendEmail("a@b.com",Emails::composeEmailShipped);
//      genericExporter.export(this::writeBody);
   }
   public void writeBody(Writer writer) {

   }
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
@Service
class EmailSender {
   @FunctionalInterface
   interface EmailComposer {
      void compose(Email email);
   }
   public void sendEmail(String emailAddress, EmailComposer composer) {
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