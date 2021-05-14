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
   }

   @Autowired
   EmailSender sender;
   @Autowired
   AllEmails emails;

   private void placeOrder() {
      // other logic
      sender.sendEmail("a@b.com", email -> emails.composeOrderReceivedEmail(email));
   }

   private void shipOrder() {
      // other logic
      sender.sendEmail("a@b.com", emails::composeOrderShippedEmail);
      // TODO send order shipped email 'similar to how send order received was implemented'
      // TODO URLEncoder.encode
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

@Component
class AllEmails {
   public void composeOrderReceivedEmail(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }

   public void composeOrderShippedEmail(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("Ti-am trimis coletu. Speram sa ajunga ( de data asta :)");
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