package victor.training.oo.behavioral.template;

import java.util.List;
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
	private EmailService emailService;
	
	public void run(String... args) throws Exception {
		// UC1: send email for order received
		//gasesc in lista p'ala de received
		emailService.sendOrderReceivedEmail("a@b.com", new OrderReceivedEmailFiller());
		// UC2: send email for order shipped
		emailService.sendOrderReceivedEmail("a@b.com" ,new OrderShippedEmailFiller());
	}
}

@Service
class EmailService {

	public void sendOrderReceivedEmail(String emailAddress, EmailFiller filler) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
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
		email.setBody("Ti-am trimas. Speram s-ajunga (de dat aasta)");
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