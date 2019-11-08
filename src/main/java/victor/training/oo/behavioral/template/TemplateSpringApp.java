package victor.training.oo.behavioral.template;

import java.util.Random;

import lombok.RequiredArgsConstructor;
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
	private EmailSender sender;
	
	public void run(String... args) {
		//UC2342
		sender.send("a@b.com", new OrderReceivedEmailFiller());
		// UC232
		sender.send("a@b.com", new OrderShippedEmailFiller());
//		Hackareala.sendOrderShippedEmail("a@b.com");
	}
}

@RequiredArgsConstructor
@Service
class EmailSender {

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

interface ContentFiller {
	void fillContent(Email email);
}
@Service
class OrderReceivedEmailFiller implements ContentFiller {
	public void fillContent(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}

@Service
class OrderShippedEmailFiller implements ContentFiller {
	public void fillContent(Email email) {
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