package victor.training.patterns.template;

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
      emailSender.sendEmail("a@b.com", new OrderShippedEmailComposer());
   }
}

@Service
class EmailSender {
//   private final EmailComposer composer;
//
//   public EmailSender(EmailComposer composer) {
//      this.composer = composer;
//   }

   public void sendEmail(String emailAddress, EmailComposer composer) { //6 teste
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      init();
      try {
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
         sendNotification();
         throw new RuntimeException("Can't send email", e);
      }
   }

   public void init() {
      System.out.println("Eu");
   }

   protected void sendNotification() {
      // hook methods: subclasele POT sa opteze sa suprascrie metoda asta.
   }
}

interface EmailComposer {
   void composeEmail(Email email);
}

class OrderPlacedEmailComposer implements EmailComposer {
   public void composeEmail(Email email) {
      // 15 linii de cod ce necesita 7 teste total
      email.setSubject("Order Placed");
      email.setBody("Thank you for your order");
   }
}

class OrderShippedEmailComposer implements EmailComposer {
   public void composeEmail(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("Ti-am trimas!");
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