package victor.training.patterns.behavioral.template;

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
      new EmailSender(new OrderPlacedEmailSender()).sendEmail("a@b.com");
   }

   private void shipOrder() {
      // more logic
      new EmailSender(new OrderShippedEmailSender()).sendEmail("a@b.com");
      // TODO evolutie: implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode
   }
}

class EmailSender {
   private final EmailWriter emailWriter;

   EmailSender(EmailWriter emailWriter) {
      this.emailWriter = emailWriter;
   }

   public void sendEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/); // interactiune stateful cu o compo extern
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            emailWriter.writeEmail(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }
}

class OrderPlacedEmailSender implements EmailWriter {
   public void writeEmail(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }
}

class OrderShippedEmailSender implements EmailWriter {
   public void writeEmail(Email email) {
//      email.digitalySign();
      email.setSubject("Order Shipped!");
      email.setBody("We've shipped you your order.");
   }
}

interface EmailWriter {
   void writeEmail(Email email);
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