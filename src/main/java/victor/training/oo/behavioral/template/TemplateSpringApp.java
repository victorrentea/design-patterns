package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TemplateSpringApp.class, args);
    }

//    @Autowired
//    private EmailSender service;

    public void run(String... args) {
        new EmailSender(new OrderReceivedEmailWriter()).sendEmail("a@b.com");
        new EmailSender(new OrderShippedEmailWriter()).sendEmail("a@b.com");
    }
}

//@Service
class EmailSender {

    private final EmailContentWriter emailWriter;

    public EmailSender(EmailContentWriter emailWriter) {
        this.emailWriter = emailWriter;
    }

    public void sendEmail(String emailAddress) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            emailWriter.write(email);
            boolean success = context.send(email);
            if (success) break;
        }
    }

}
interface EmailContentWriter {
    void write(Email email);
}

class OrderReceivedEmailWriter implements EmailContentWriter {
    public void write(Email email) {
        email.setSubject("Order Received");
        email.setBody("Thank you for your order");
    }
}

class OrderShippedEmailWriter implements EmailContentWriter {
    public void write(Email email) {
        email.setSubject("Order Shipped");
        email.setBody("Ti-am trimas! Speram s-ajunga (de data asta)!");
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
    private String sender;
    private String subject;
    private String body;
    private String replyTo;
    private String to;
}