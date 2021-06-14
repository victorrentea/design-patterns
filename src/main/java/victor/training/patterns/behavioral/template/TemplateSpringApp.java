package victor.training.patterns.behavioral.template;

import lombok.Data;
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

	public void run(String... args) {
		placeOrder();
		shipOrder();
	}

	private void placeOrder() {
		// other logic
		new EmailSender().sendEmail("a@b.com", new OrderReceivedEmailWriter());
	}

	private void shipOrder() {
		// other logic
		// TODO send order shipped email 'similar to how send order received was implemented'
		new EmailSender().sendEmail("a@b.com", new OrderShippedEmailWriter());

		// TODO URLEncoder.encode
	}
}

@Service
class EmailSender {

	public void sendEmail(String emailAddress, EmailWriter emailWriter) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		try {
			for (int i = 0; i < MAX_RETRIES; i++) {
				Email email = new Email(); // constructor generates new unique ID
				email.setSender("noreply@corp.com");
				email.setReplyTo("/dev/null");
				email.setTo(emailAddress);
				emailWriter.writeEmail(email);
				boolean success = context.send(email);
				if (success) break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can't send email", e);
		}
	}

}

@Service
class OrderReceivedEmailWriter implements EmailWriter {
	public void writeEmail(Email email) {
		email.setSubject("Order Received!");
		email.setBody("Thank you for your order");
	}
}

@Service
class OrderShippedEmailWriter implements EmailWriter {
	public void writeEmail(Email email) {
		email.setSubject("Order Shipped!");
		email.setBody("The order is on its way. Hope it gets to you this time. :)");
	}
}

interface EmailWriter {
	void writeEmail(Email email);

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