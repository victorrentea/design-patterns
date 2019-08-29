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
//    private EmailService service;

    public void run(String... args) {
        new EmailSender(new OrderReceivedEmailFiller()).sendEmail("a@b.com");
        // order shipped
        new EmailSender(new OrderShippedEmailFiller()).sendEmail("a@b.com");
    }
}

class EmailSender {
    private final EmailFiller emailFiller;
    public EmailSender(EmailFiller emailFiller) {
        this.emailFiller = emailFiller;
    }

    public void sendEmail(String emailAddress) {
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

interface EmailFiller {
    void setEmailContent(Email2 email);
}

class OrderReceivedEmailFiller implements EmailFiller {
    @Override
    public void setEmailContent(Email2 email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}

class OrderShippedEmailFiller implements EmailFiller {
    @Override
    public void setEmailContent(Email2 email) {
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