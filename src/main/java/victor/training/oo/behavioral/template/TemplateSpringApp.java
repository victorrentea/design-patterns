package victor.training.oo.behavioral.template;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.Data;
import victor.training.oo.behavioral.template.EmailService.EmailFiller;

@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}

	@Autowired
	private EmailService emailService;
	@Autowired
	private Emails emails;
	public void run(String... args) throws Exception {
		emailService.sendEmail("a@b.com", emails::fillOrderReceivedEmail);
		emailService.sendEmail("a@b.com", emails::fillEmailShippedEmail);
	}
}

@Service
class EmailService {

	@FunctionalInterface
	interface EmailFiller {
		void fillEmail(Email email);
	}
	public void sendEmail(String emailAddress, EmailFiller emailFiller) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			emailFiller.fillEmail(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
}

@Repository
class SomeRepo {

	public String getStuff() {
		return null; // TODO
	}}

@Service
class Emails {
	@Autowired
	private SomeRepo repo;
	public void fillOrderReceivedEmail(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order" + repo.getStuff());
	}
	public void fillEmailShippedEmail(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("He just sent you your order. Hope it gets to you (this time).");
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