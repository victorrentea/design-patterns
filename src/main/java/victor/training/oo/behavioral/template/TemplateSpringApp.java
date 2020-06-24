package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;
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
      emailSender.sendEmail("a@b.com", new OrderReceivedEmailSender());
   }

   private void shipOrder() {
      // other logic
      emailSender.sendEmail("a@b.com", new OrderShippedEmailSender());
      // TODO send order shipped email 'similar to how send order received was implemented'
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
         composer.composeEmail(email);
         boolean success = context.send(email);
         if (success) break;
      }
   }
}
@FunctionalInterface //-- imi place
// pero no me gusta default int x () { reutrn 1;}
interface EmailComposer {
   void composeEmail(Email email);
}
@Service
class OrderReceivedEmailSender implements EmailComposer {
   public void composeEmail(Email email) {
      email.setSubject("Order Received");
      email.setBody("Thank you for your order");
   }
}
@Service
class OrderShippedEmailSender implements EmailComposer {
   public void composeEmail(Email email) {
      email.setSubject("Order Shipped");
      email.setBody("Ti-am trimis. Speram sa ajunga * de data asta");
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


//interface I1 {
//   default int x() {
//      return 1;
//   }
//}
//interface I2 {
//   default int x() {
//      return 2;
//   }
//}
//
//class A implements I1,I2 {
////   @Override
////   public int x() {
////      return 0;
////   }
//}

interface ICsvWriter {
   void writeCell(String cell);
   void writeLine();

   default void writeCells(List<String> cells) {
      cells.forEach(this::writeCell); // imbogatesti API-ul cu functii derivate din fct abstracte din interfata
   }

   default void writeCellsUpper(List<String> cells) { // apelezi tu niste functii Util/static
      cells.stream().map(String::toUpperCase).forEach(this::writeCell);
   }

   // din java 9+
//    private void checkValidCellValue(String cell) {
////      if //
//   }
}


