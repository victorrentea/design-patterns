package victor.training.oo.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}

	@Autowired
	private EmailSender sender;

	public void run(String... args) {
		//US1
		sender.sendEmail("a@b.com",new OrderReceivedEmailComposer());
		//US7
		sender.sendEmail("a@b.com",new OrderShippedEmailComposer());
	}
}
@Service
class EmailSender {
	public void sendEmail(String emailAddress, EmailComposer composer) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
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

interface EmailComposer {
	void composeEmail(Email email);
}
class OrderReceivedEmailComposer implements EmailComposer {
	public void composeEmail(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
class OrderShippedEmailComposer implements EmailComposer {
	public void composeEmail(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Ti-am trimis. Speram sa ajunga (de data asta).");
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