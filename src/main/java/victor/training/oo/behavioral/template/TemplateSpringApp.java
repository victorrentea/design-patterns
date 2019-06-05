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
	private AbstractEmailService service;
	
	public void run(String... args) {
		service.sendOrderReceivedEmail("a@b.com");
		service.sendOrderReceivedEmail("a@b.com");
	}
}

@Service
abstract
class AbstractEmailService {

	public void sendOrderReceivedEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			fillContent(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}

	protected abstract void fillContent(Email email);
}
class OrderReceivedEmailSender extends AbstractEmailService {
	@Override
	protected void fillContent(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}


class OrderShippedEmailService extends AbstractEmailService {
	@Override
	protected void fillContent(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("We've shipped your order. Hope it gets to you (this time).");
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