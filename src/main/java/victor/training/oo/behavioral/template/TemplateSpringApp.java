package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private OrderReceivedEmailEnricher receivedEmailEnricher;
	@Autowired
	private OrderShippedEmailEnricher shippedEmailEnricher;

	public void run(String... args) {
		emailSender.sendEmail("a@b.com", receivedEmailEnricher);
		emailSender.sendEmail("a@b.com", shippedEmailEnricher);
//		new OrderShippedEmailEnricher().sendEmail("a@b.com");
	}
}

@Service
class EmailSender {

	public void sendEmail(String emailAddress, EmailEnricher enricher) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			enricher.enrich(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}

}
interface EmailEnricher {
	void enrich(Email email);

}
@Service
class OrderReceivedEmailEnricher implements EmailEnricher {
	public void enrich(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}

@Service
class OrderShippedEmailEnricher implements EmailEnricher {
	public void enrich(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("We've sent you.");
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