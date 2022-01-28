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
      emailSender.sendOrderPlacedEmail("a@b.com");
   }

   private void shipOrder() {
      // more logic
      emailSender.sendOrderShippedEmail("a@b.com");
      // TODO URLEncoder.encode
   }
}

@Service
class EmailSender {

   private static void composeOrderShippedEmail(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("We've shipped your your groceries.");
   }


   public void sendOrderShippedEmail(String emailAddress) {
      sendEmail(emailAddress, EmailSender::composeOrderShippedEmail);
   }

   private static void composeOrderReceivedEmail(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");

//      encrypt(email)
   }

   public void sendOrderPlacedEmail(String emailAddress) {
      sendEmail(emailAddress, EmailSender::composeOrderReceivedEmail);
   }

   private void sendEmail(String emailAddress, EmailComposer composer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      try {
         int MAX_RETRIES = 3;
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            composer.composeEmail(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

}

class AllEmails {

}

interface EmailComposer {
   void composeEmail(Email email);
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