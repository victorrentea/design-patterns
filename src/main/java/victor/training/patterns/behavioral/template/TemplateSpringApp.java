package victor.training.patterns.behavioral.template;

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

	public void run(String... args) {
		placeOrder();
		shipOrder();
	}

	private void placeOrder() {
		// other logic
		new EmailService().sendOrderReceivedEmail("a@b.com");
	}

	private void shipOrder() {
		// other logic
		// TODO send order shipped email 'similar to how send order received was implemented'
		new EmailService().sendOrderShippedEmail("a@b.com");

		// TODO URLEncoder.encode
	}
}

class EmailService {

	public void sendOrderShippedEmail(String emailAddress) {
		sendEmail(emailAddress, "Order Shipped");
	}

	public void sendOrderReceivedEmail(String emailAddress) {
		sendEmail(emailAddress, "Order Received!");
	}

	private void sendEmail(String emailAddress, String subject) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		try {
			for (int i = 0; i < MAX_RETRIES; i++) {
				Email email = new Email(); // constructor generates new unique ID
				email.setSender("noreply@corp.com");
				email.setReplyTo("/dev/null");
				email.setTo(emailAddress);
				email.setSubject(subject);
				email.setBody("Thank you for your order");
				boolean success = context.send(email);
				if (success) break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can't send email", e);
		}
	}

}
//				email.setSubject("Order Shipped!");
//				email.setBody("The order is on its way. Hope it gets to you this time. :)");

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