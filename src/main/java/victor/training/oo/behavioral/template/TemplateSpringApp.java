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
	private Triunghiuletz triunghiuletz;
	@Autowired
	private Patratzel patratzel;
	
	public void run(String... args) throws Exception {
		triunghiuletz.sendOrderReceivedEmail("a@b.com");
		patratzel.sendOrderReceivedEmail("a@b.com");
	}
}

abstract class EmailService {

	public void sendOrderReceivedEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			p(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
	protected abstract void p(Email email);

}
@Service
class Triunghiuletz  extends EmailService{
	protected void p(Email email) {
		// triunghiuletz
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
@Service
class Patratzel extends EmailService {
	protected void p(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Ti-am trimas! Speram s-ajunga (de data asta)!");
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