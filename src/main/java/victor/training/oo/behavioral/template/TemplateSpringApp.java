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
	private OrderReceivedEmailSender orderReceivedEmailSender;
	@Autowired
	private OrderShippedEmailSender orderShippedEmailSender;

	public void run(String... args) {
		orderReceivedEmailSender.sendEmail("a@b.com");
		orderShippedEmailSender.sendEmail("a@b.com");
//		service.sendOrderShippedEmail("a@b.com");

	}
}

abstract class AbstractEmailSender {

	public void sendEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			composeEmail(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
	protected abstract void composeEmail(Email email);
}
@Service
class OrderReceivedEmailSender extends AbstractEmailSender {
	protected void composeEmail(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
@Service
class OrderShippedEmailSender extends AbstractEmailSender {
	@Override
	protected void composeEmail(Email email) {
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