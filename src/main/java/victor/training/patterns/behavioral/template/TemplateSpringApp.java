package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;


// Data Service Impl Manager = cuvinte inutile


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
   EmailService emailService;

   private void placeOrder() {
      // other logic
      emailService.sendOrderReceivedEmail("a@b.com");
   }

   private void shipOrder() {
      // other logic
      // TODO send order shipped email 'similar to how send order received was implemented'
      // TODO URLEncoder.encode
     emailService.sendOrderShippedEmail("a@b.com");
   }
}

@Service
class EmailService {// Email sending facade
   @Autowired
   AllEmails allEmails;
   @Autowired
   private EmailSender sender;

   public void sendOrderReceivedEmail(String emailAddress) {
      sender.sendEmail(emailAddress, allEmails::composeOrderReceived);
   }

   public void sendOrderShippedEmail(String emailAddress) {
      sender.sendEmail(emailAddress, allEmails::composeEmailShipped);
   }
}

@Service
class EmailSender {

   interface EmailComposer {
      void compose(Email email);
   }

   public void sendEmail(String emailAddress, EmailComposer composer) {
      EmailContext context = new EmailContext(/*smtpConfig,etc*/);
      int MAX_RETRIES = 3;
      try {
         for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            composer.compose(email);
            boolean success = context.send(email);
            if (success) break;
         }
      } catch (Exception e) {
         throw new RuntimeException("Can't send email", e);
      }
   }

}

@Component
class AllEmails {
   public void composeOrderReceived(Email email) {
      email.setSubject("Order Received!");
      email.setBody("Thank you for your order");
   }

   public void composeEmailShipped(Email email) {
      email.setSubject("Order Shipped!");
      email.setBody("Ti-am trimis coletul. Speram sa ajunga (de data asta).");
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
//   public void betterExample() {
//
//      try (FileWriter writer = new FileWriter(new File(parentFolder, "out.txt"))) {
//
//
//         f(writer);
//
//      } catch (IOException e) {
//         // curat fisier
//         throw new RuntimeException(e);
//      }
//   }
//   private void f(FileWriter writer) throws IOException {
//      writer.write("A");
//      for (i) {
//         writer.write("B");
//      }
//      writer.write("C");
//   }
//   private void g(FileWriter writer) throws IOException {
//      writer.write("X");
//   }
