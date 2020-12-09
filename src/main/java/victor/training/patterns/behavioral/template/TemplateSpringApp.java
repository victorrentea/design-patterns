package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.apache.commons.codec.Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

public class TemplateSpringApp {
   public static void main(String[] args) {

      placeOrder();
      shipOrder();
   }

   private static void placeOrder() {
      // other logic
      new EmailSender().sendEmail("a@b.com", AllEmails::composeOrderReceivedEmail);

   }

   private static void shipOrder() {
      // other logic
      // TODO send order shipped email 'similar to how send order received was implemented'
      new EmailSender().sendEmail("a@b.com", AllEmails::composeOrderShippedEmail);
   }
}

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

class AllEmails {
   public static void composeOrderReceivedEmail(Email email) {
      email.setSubject("Order Received");
      email.setBody("Thank you for your order");
   }
   public static void composeOrderShippedEmail(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("Ti-am trimis, speram sa ajunga de data asta (dupa anu nou)");
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