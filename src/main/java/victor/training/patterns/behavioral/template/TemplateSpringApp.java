package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
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

	@Autowired
	EmailSender sender;
	@Autowired
	Emails emails;

	private void placeOrder() {
		// other logic
		sender.sendEmail("a@b.com", emails::writeEmailReceivedContent);
	}

	private void shipOrder() {
		// other logic
		sender.sendEmail("a@b.com", email -> emails.writeEmailShippedContent(email));
		// TODO send order shipped email 'similar to how send order received was implemented'
		// TODO URLEncoder.encode
	}
}

@Service
class EmailSender {
//	private EmailContentWriter writer;
public void sendEmail(String emailAddress, EmailContentWriter writer) {
	EmailContext context = new EmailContext(/*smtpConfig,etc*/);
	int MAX_RETRIES = 3;
	try {
		for (int i = 0; i < MAX_RETRIES; i++) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			writer.writeContent(email);
			boolean success = context.send(email);
			if (success) break;
		}
	} catch (Exception e) {
		throw new RuntimeException("Can't send email", e);
	}
}

	interface EmailContentWriter {
		void writeContent(Email email);
	}
}
//@Service
//@RequiredArgsConstructor
//class A {
//	private final B b;
//}
//@Service
//@RequiredArgsConstructor
//class B {
//	private final A a;
//}

@Component
class Emails {
	public void writeEmailReceivedContent(Email email) {
		email.setSubject("Order Received!");
		email.setBody("Thank you for your order");
	}

	public void writeEmailShippedContent(Email email) {
		email.setSubject("Order Shipped!");
		email.setBody("Vezi ca vine livrarea (cand tu nu esti acasa)");
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