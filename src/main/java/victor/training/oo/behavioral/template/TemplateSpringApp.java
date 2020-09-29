package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
   private EmailSender orderShippedEmailSender;
   @Autowired
   private EmailSender orderReceivedEmailSender;
   private void placeOrder() {
      // other logic
//      new EmailSender(new OrderReceivedEmailComposer()).sendEmail("a@b.com");
      orderReceivedEmailSender.sendEmail("a@b.com");
   }

   private void shipOrder() {
      // other logic
      // TODO send order shipped email 'similar to how send order received was implemented'
//      new EmailSender(new OrderShippedEmailComposer()).sendEmail("a@b.com");
      orderShippedEmailSender.sendEmail("a@b.com");
   }

   @Bean
   public EmailSender orderShippedEmailSender(OrderShippedEmailComposer composer) {
      return new EmailSender(composer);
   }
   @Bean
   public EmailSender orderReceivedEmailSender(OrderReceivedEmailComposer composer) {
      return new EmailSender(composer);
   }
}

class EmailSender {
   private final EmailComposer composer;

   public EmailSender(EmailComposer composer) {
      this.composer = composer;
   }

   public void sendEmail(String emailAddress) {
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