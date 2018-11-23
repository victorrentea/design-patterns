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
	private OrderReceivedEmailFiller orderReceivedEmailSender;
	@Autowired
	private OrderShippedEmailFiller orderShippedEmailSender;
	
	public void run(String... args) throws Exception {
		new EmailSender(orderReceivedEmailSender).sendEmail("a@b.com");
		new EmailSender(orderShippedEmailSender).sendEmail("a@b.com");
	}
}

@Data
class EmailSender {
	private final EmailFiller filler;
	
	public void sendEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int FISE = 3;
		for (int i = 0; i < FISE; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			filler.fill(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
}

interface EmailFiller {
	void fill(Email email);
}

@Service
class OrderReceivedEmailFiller implements EmailFiller {
	public void fill(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
@Service
class OrderShippedEmailFiller implements EmailFiller {
	public void fill(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Ti-am trimas, speram s-ajunga! (de data asta)");
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