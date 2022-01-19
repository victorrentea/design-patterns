package victor.training.patterns.behavioral.template;

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
      new OrderPlacedEmailSender().sendEmail("a@b.com");
   }

   private void shipOrder() {
      // more logic
      // TODO implement 'similar to how order placed email was implemented'
      // TODO URLEncoder.encode
      new OrderShippedEmailSender().sendEmail("a@b.com");
   }
}

abstract class AbstractEmailSender {
   public void sendEmail(String emailAddress) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            compose(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

   protected abstract void compose(Email email);
}

class OrderPlacedEmailSender extends AbstractEmailSender {
   @Override
   protected void compose(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }
}

class OrderShippedEmailSender extends AbstractEmailSender {
   @Override
   protected void compose(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("Ti-am trimis, speram sa ajunga (de data astA)");

   }
}


class EmailContext {
   public boolean send(Email email) {
      System.out.println("Trying to send " + email);
      return new Random(System.nanoTime()).nextBoolean();
   }
}

