package victor.training.patterns.behavioral.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	Emails emails;

	private void placeOrder() {
		// other logic
		new EmailSender().sendOrderReceivedEmail("a@b.com");
	}

	private void shipOrder() {
		// other logic
		// TODO send order shipped email 'similar to how send order received was implemented'
		new EmailSender().sendEmail("a@b.com", emails::writeShippedEmail);

		// TODO URLEncoder.encode
	}
}

@Service
class EmailSender {


	@Autowired
	Emails emails;

	public void sendOrderReceivedEmail(String emailAddress) { // best: it also hides the "Emails" class from our clients.
		sendEmail(emailAddress, emails::writeReceivedEmail);
	}


	@FunctionalInterface
	interface EmailWriter {
		void writeEmail(Email email); // BAD because it involves MUTATIONS in Email. Setter (don't show Sander this)
	}

	public void sendEmail(String emailAddress, EmailWriter emailWriter) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		try {
			for (int i = 0; i < MAX_RETRIES; i++) {
				Email email = new Email(); // constructor generates new unique ID
				email.setSender("noreply@corp.com");
				email.setReplyTo("/dev/null");
				email.setTo(emailAddress);
				emailWriter.writeEmail(email);
				boolean success = context.send(email);
				if (success) break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can't send email", e);
		}
	}

}
//			() ->  new SubjectAndBody("Order Received!", "Thank you for your order");

// Extreme SRP --> too short classes > BAD
//interface Email {
//	String getSubject();
//	String getBody();
//}

//interface Email {
//	SubjectAndBody getContent(); // immutable tiny object, still possible labmdas.
//}

@Service
class Emails {
	public void writeReceivedEmail(Email email) {
		email.setSubject("Order Received!");
		email.setBody("Thank you for your order");
	}

	public void writeShippedEmail(Email email) {
		email.setSubject("Order Shipped!");
		email.setBody("The order is on its way. Hope it gets to you this time. :)");
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