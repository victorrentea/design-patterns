package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
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
      // other logic
      emailSender.sendEmail("a@b.com",new OrderReceivedEmailComposer());
   }

   private void shipOrder() {
      // other logic
      // TODO send order shipped email 'similar to how send order received was implemented'
      emailSender.sendEmail("a@b.com",new OrderShippedEmailComposer());
   }

}

@Service
class EmailSender {

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

interface EmailComposer {
   void compose(Email email);
}

@Primary
@Service
class OrderReceivedEmailComposer implements  EmailComposer {
   public void compose(Email email) {
      email.setSubject("Order Received");
      email.setBody("Thank you for your order");
   }
}

@Service
class OrderShippedEmailComposer implements  EmailComposer {
   public void compose(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("We've sent you your order. Hope it gets in one piece (this time).");
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