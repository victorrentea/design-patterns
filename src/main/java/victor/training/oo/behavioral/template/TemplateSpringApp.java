package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TemplateSpringApp.class, args);
    }

    @Autowired
    private EmailSender sender;

    @Autowired
    private EmailFillers fillers;

    public void run(String... args) {
        sender.sendEmail("a@b.com", fillers::fillEmailReceived);
        // order shipped
        sender.sendEmail("a@b.com", fillers::fillEmailShipped);
    }
}
@Service
class EmailSender {

    @FunctionalInterface
    interface EmailFiller {
        void setEmailContent(Email2 email);
    }
    public void sendEmail(String emailAddress, EmailFiller emailFiller) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++) {
            Email2 email = new Email2(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            emailFiller.setEmailContent(email);
            boolean success = context.send(email);
            if (success) break;
        }
    }
}
@Service
class EmailFillers {
    public void fillEmailReceived(Email2 email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
    public void fillEmailShipped(Email2 email) {
        email.setSubject("Order Shipped");
        email.setBody("Ti-am trimas. Speram s-ajunga. de data asta.");
    }
}

class EmailContext {
    public boolean send(Email2 email) {
        System.out.println("Trying to send " + email);
        return new Random(System.nanoTime()).nextBoolean();
    }
}

@Data
class Email2 {
    private String subject;
    private String body;
    private final long id = new Random(System.nanoTime()).nextLong();
    private String sender;
    private String replyTo;
    private String to;
}