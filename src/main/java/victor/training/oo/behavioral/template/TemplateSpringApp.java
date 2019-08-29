package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import lombok.Data;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TemplateSpringApp.class, args);
    }

//    @Autowired
//    private EmailService service;

    public void run(String... args) {
        new OrderReceivedEmailSender().sendEmail("a@b.com");
        // order shipped
		new OrderShippedEmailSender().sendEmail("a@b.com");
    }
}

@Service
abstract
class AbstractEmailSender {

    public void sendEmail(String emailAddress) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++) {
            Email2 email = new Email2(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            setEmailContent(email);
            boolean success = context.send(email);
            if (success) break;
        }
    }

    protected abstract void setEmailContent(Email2 email);
}
class OrderReceivedEmailSender extends AbstractEmailSender {
	protected void setEmailContent(Email2 email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}

class OrderShippedEmailSender extends AbstractEmailSender {
    @Override
    protected void setEmailContent(Email2 email) {
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