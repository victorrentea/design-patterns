package victor.training.patterns.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
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

   @Autowired
   private ApplicationEventPublisher eventPublisher;

   private void placeOrder() {
      emailSender.sendOrderPlacedEmail("a@b.com"); // a bit strange to have senders in front of my client.
      // it couples my clients to Senders.

//      new OrderPlacedEmailSender().sendOrderPlacedEmail("a@b.com");
   }

   private void shipOrder() {
      // logic
     emailSender.sendOrderShippedEmail("a@b.com");
      // TODO implement 'similar to how order placed email was implemented'
   }
}
@Service
class EmailSender {

   @FunctionalInterface
   interface  EmailComposer {
      void compose(Email email);
   }

   public void sendOrderShippedEmail(String emailAddress) {
      sendEmail(emailAddress, this::composeOrderShipped);
   }
   public void sendOrderPlacedEmail(String emailAddress) {
      sendEmail(emailAddress, this::composeOrderPlaced);
   }
   private void sendEmail(String emailAddress, EmailComposer emailComposer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         // TODO Template method is a cyclic dependency, that;s why this pattern heavily used in 2000s is now an anti-pattern
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            emailComposer.compose(email);
            boolean success = context.send(email);
            if (success) break;
         }
//         hookEmptyMethod();
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }
   public void composeOrderShipped(Email email) {

      // ARBITRRAY CODE BELOW
      email.setSubject("Order Shipped");
      email.setBody("WE sent you the jogger.");
      // perhaps attach an attachemtn
   }
   public void composeOrderPlaced(Email email) {
      // ARBITRRAY CODE BELOW
      email.setSubject("Order Placed");
      email.setBody("Thank you for your order");
      // perhaps attach an attachemtn
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