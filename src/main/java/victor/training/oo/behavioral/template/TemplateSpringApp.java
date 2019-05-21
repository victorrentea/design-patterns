package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private OrderReceivedEmailComposer received;
	@Autowired
	private OrderShippedEmailComposer shipped;

	public void run(String... args) throws Exception {
		new EmailService(received).sendEmail("a@b.com");
		new EmailService(shipped).sendEmail("a@b.com");
//		shipped.sendEmail("a@b.com");
	}
}
class EmailService {
	private final EmailComposer composer;

	EmailService(EmailComposer composer) {
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
@Service
class OrderReceivedEmailComposer implements EmailComposer {
	public void compose(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
@Service
class OrderShippedEmailComposer implements EmailComposer {
	public void compose(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Tz-am trimas! Speram s-ajunga (de data asta)");
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