package victor.training.oo.behavioral.template;

import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import lombok.Data;

@RequiredArgsConstructor
@SpringBootApplication
public class TemplateSpringApp implements CommandLineRunner {

	private final EmailSender sender;
	private final Emails emails;

	public static void main(String[] args) {
		SpringApplication.run(TemplateSpringApp.class, args);
	}



	public void run(String... args) {
		//UC2342
		sender.send("a@b.com", emails::fillOrderReceivedContent);
		// UC232
		sender.send("a@b.com", emails::fillOrderShippedContent);
//		Hackareala.sendOrderShippedEmail("a@b.com");
	}
}

@RequiredArgsConstructor
@Service
class EmailSender {
	@FunctionalInterface
	interface ContentFiller {
		void fillContent(Email email);
	}
	public void send(String emailAddress, ContentFiller filler) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			filler.fillContent(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
}

@Service
class Emails {
	public void fillOrderReceivedContent(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
	public void fillOrderShippedContent(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Ti-am trimas, Speram sa ajunga");
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