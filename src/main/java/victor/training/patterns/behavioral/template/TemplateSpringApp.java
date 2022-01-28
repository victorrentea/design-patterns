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
      emailSender.sendEmail("a@b.com", new OrderReceivedEmailComposer());
   }

   private void shipOrder() {
      // more logic
      emailSender.sendEmail("a@b.com", new OrderShippedEmailComposer());
      // TODO implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode
   }
}

//enum EmailType {
//   ORDER_PLACED("Subj", "Body")
//}

//@Service // IMPOSSIBLE as there is use-case specific logic ! >>> this is STATEFUL !


@Service
class EmailSender {

   public void sendEmail(String emailAddress, EmailComposer composer) {
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

class OrderReceivedEmailComposer implements EmailComposer {
   @Override
   public void composeEmail(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
//      encrypt(email)
   }
}

class OrderShippedEmailComposer implements EmailComposer {
   @Override
   public void composeEmail(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("We've shipped your your groceries.");
   }
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