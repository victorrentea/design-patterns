package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {

	@Autowired
	private EmailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}

	public void run(String... args) {
		placeOrder();
		shipOrder();
	}

	private void placeOrder() {
		// other logic
		emailSender.sendEmail("a@b.com", new OrderPlacedEmailComposer());
	}

	private void shipOrder() {
		// other logic
		emailSender.sendEmail("a@b.com", new OrderShippedEmailComposer());
		// TODO send order shipped email 'similar to how send order received was implemented'
		// TODO URLEncoder.encode
	}
}

@Service
class EmailSender {

	public void sendEmail(String emailAddress, EmailComposer composer) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		try {
			for (int i = 0; i < MAX_RETRIES; i++) {
				Email email = new Email(); // constructor generates new unique ID
				email.setSender("noreply@corp.com");
				email.setReplyTo("/dev/null");
				email.setTo(emailAddress);
				composer.compose(email);
				boolean success = context.send(email);
				if (success) break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can't send email", e);
		}
	}

}

@Service
class OrderPlacedEmailComposer implements EmailComposer {
	public void compose(Email email) {
		// MOre logic
		email.setSubject("Order Received!");
		email.setBody("Thank you for your order");
	}
}

@Service
class OrderShippedEmailComposer implements EmailComposer {
	public void compose(Email email) {
		email.setSubject("Order Shipped!");
		email.setBody("Your order has been shipped to you.");
	}
}

interface EmailComposer {
	void compose(Email email);
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