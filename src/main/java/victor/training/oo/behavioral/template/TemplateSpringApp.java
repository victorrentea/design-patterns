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


    public void run(String... args) {
        new EmailSender(new OrderReceivedEmailFiller()).sendEmail("a@b.com");
        new EmailSender(new OrderShippedEmailFiller()).sendEmail("a@b.com");
    }
}

class EmailSender {
    private final EmailContentFiller filler;

    public EmailSender(EmailContentFiller filler) {
        this.filler = filler;
    }

    public void sendEmail(String emailAddress) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            filler.fillContent(email);
            boolean success = context.send(email);
            if (success) break;
        }
    }

}
interface EmailContentFiller {
    void fillContent(Email email);
}

class OrderReceivedEmailFiller implements EmailContentFiller {
    @Override
    public void fillContent(Email email) {
        email.setSubject("Order Received");
        email.setBody("Thank you for your order");
    }
}

class OrderShippedEmailFiller implements EmailContentFiller {
    @Override
    public void fillContent(Email email) {
        email.setSubject("Order Shipped");
        email.setBody("Shipped it to you. Hope it gets to you *this time*.:)");
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