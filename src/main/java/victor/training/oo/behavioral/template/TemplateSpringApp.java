package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.xml.ws.ServiceMode;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TemplateSpringApp.class, args);
    }

    @Inject
    private EmailSender emailSender;
    @Inject
    private AllEmails allEmails;

    public void run(String... args) {
        emailSender.sendEmail("a@b.com", allEmails::fillOrderReceivedEmail);
        emailSender.sendEmail("a@b.com", allEmails::fillOrderShippedEmail);
    }
}

@Service
class EmailSender {

    interface EmailContentFiller {
        void fillContent(Email email);
    }
    public void sendEmail(String emailAddress, EmailContentFiller filler) {
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

@Service
class AllEmails {
    public void fillOrderReceivedEmail(Email email) {
        email.setSubject("Order Received");
        email.setBody("Thank you for your order");
    }

    public void fillOrderShippedEmail(Email email) {
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