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
   @Autowired
   Emails emails;

   private void placeOrder() {
      // other logic
      emailSender.sendEmail("a@b.com", emails::composeOrderReceivedEmail);
   }

   private void shipOrder() {
      // other logic
      // TODO send order shipped email 'similar to how send order received was implemented'
      emailSender.sendEmail("a@b.com", email -> emails.composeOrderShippedEmail(email));
   }
}

//@Stateless /
@Service
// -- nu merge o singura instanta
class EmailSender {
//	@Autowired // @Resource @PersistenceContext
//	private EmailClient client;

   @FunctionalInterface
   public interface EmailComposer {
      void compose(Email email);
   }
   public void sendEmail(String emailAddress, EmailComposer emailComposer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      for (int i = 0; i < MAX_RETRIES; i++) {
         Email email = new Email(); // constructor generates new unique ID
         email.setSender("noreply@corp.com");
         email.setReplyTo("/dev/null");
         email.setTo(emailAddress);
         emailComposer.compose(email);
         boolean success = context.send(email);
         if (success) break;
      }
   }
}



@Service
class Emails {
   public void composeOrderReceivedEmail(Email email) {
      email.setSubject("Order Received");
      email.setBody("Thank you for your order");
   }

   public void composeOrderShippedEmail(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("Ti-am trimis. Speram sa ajunga (de data Asta);");
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