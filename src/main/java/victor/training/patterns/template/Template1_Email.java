package victor.training.patterns.template;

import lombok.Data;

import java.util.Random;

public class Template1_Email {
   public static void main(String[] args) {
      placeOrder();
      shipOrder();
   }

   private static void placeOrder() {
      // logic
      new EmailService().sendEmail("a@b.com", "Order Placed", "Thank you for your order");
   }
   private static void shipOrder() {
      // logic
      new EmailService().sendEmail("a@b.com", "Order Shipped", "Shipped");
   }
}

class EmailService {
   public void sendEmail(String emailAddress, String subject, String body) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            email.setSubject(subject);
            email.setBody(body);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
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