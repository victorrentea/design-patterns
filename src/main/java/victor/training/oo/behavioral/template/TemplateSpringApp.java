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
	private EmailSender sender;

	@Autowired
	private Emails emails;

	public void run(String... args) {
		//UC1
		sender.sendEmail("a@b.com", emails::composeOrderReceived);
		//UC2
		sender.sendEmail("a@b.com", emails::composeOrderShipped);
//		service.sendOrderShippedEmail("a@b.com");
	}
}

@Service
class EmailSender {

	@FunctionalInterface
	interface EmailComposer {
		void compose(Email email);
	}


//	public EmailComposer upperSubject(EmailComposer emailComposer) {
//		return email -> {
//			emailComposer.compose(email);
//			email.setSubject(email.getSubject().toUpperCase());
//		};
//	}

	public void sendEmail(String emailAddress, EmailComposer composer) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			composer.compose(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
}

@Service
class Emails {
	public void composeOrderReceived(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
	public void composeOrderShipped(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("We've sent you. Hope it gets to you this time!");
	}
}

class EmailContext{
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