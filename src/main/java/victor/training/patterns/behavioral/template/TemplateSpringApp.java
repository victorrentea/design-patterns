package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Random;

public class TemplateSpringApp implements CommandLineRunner {
   @Autowired
   private EmailSender emailSender = new EmailSender();

   public void run(String... args) {
      placeOrder();
      shipOrder();
   }

   @Autowired
   private Emails emails = new Emails();

   //   public static void main(String[] args) {
//       SpringApplication.run(TemplateSpringApp.class, args);
//   }
   public static void main(String[] args) {
      new TemplateSpringApp().run();
   }

   private void placeOrder() {
      emailSender.sendEmail("a@b.com", emails::composeOrderReceived);
   }

   private void shipOrder() {
      emailSender.sendEmail("a@b.com", emails::composeOrderShipped);

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

   @FunctionalInterface
   interface EmailComposer {
      void compose(Email email);
   }
//   protected String encodeTitle

}

class Emails {
   public void composeOrderReceived(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }

   public void composeOrderShipped(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("We've shipped you. Hope it gets to you (this time).");
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