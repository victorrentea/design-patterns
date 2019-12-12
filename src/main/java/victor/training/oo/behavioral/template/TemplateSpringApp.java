package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}

//	@Autowired
//	private EmailService service;
	
	public void run(String... args) {
		new EmailSender(new OrderPlacedEmailSender()).sendEmail("a@b.com");
		new EmailSender(new OrderShippedEmailSender()).sendEmail("a@b.com");
	}
}

//@Service
class EmailSender {
	private final EmailComposer composer;

	EmailSender(EmailComposer composer) {
		this.composer = composer;
	}

	public void sendEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
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

class OrderPlacedEmailSender implements EmailComposer {
	public void compose(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}

class OrderShippedEmailSender implements EmailComposer {
	public void compose(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("We've shipped your order. Hope it gets to you (this time)");
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