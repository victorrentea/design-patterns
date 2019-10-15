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

	
	public void run(String... args) {
		new EmailSender(new OrderReceivedEmailWriter()).sendEmail("a@b.com");
		new EmailSender(new OrderShippedEmailWriter()).sendEmail("a@b.com");
	}
}

class EmailSender {
	private final EmailWriter writer;
	
	public EmailSender(EmailWriter writer) {
		this.writer = writer;
	}

	public void sendEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		final int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			writer.writeEmail(email);
			boolean success = context.send(email);
			if (success) break;
		}
	}
}
interface EmailWriter {
	void writeEmail(Email email);
}

class OrderReceivedEmailWriter implements EmailWriter {
	@Override
	public void writeEmail(Email email) {
		email.setSubject("Order Received");
		email.setBody("Thank you for your order");
	}
}
class OrderShippedEmailWriter implements EmailWriter {
	@Override
	public void writeEmail(Email email) {
		email.setSubject("Order Shipped");
		email.setBody("Ti-am trimis. Speram sa ajunga(de data asta)");
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