package victor.training.oo.behavioral.template;

import lombok.Data;

import java.util.Random;

public class TemplateSpringApp {
	public static void main(String[] args) {
		EmailService service = new EmailService();
		service.sendOrderReceivedEmail("a@b.com", false);
		service.sendOrderReceivedEmail("a@b.com", true); // CR 323
	}
}

class EmailService {

	public void sendOrderReceivedEmail(String emailAddress, boolean cr323) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("noreply@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			if (cr323){
				email.setSubject("Order Shipped");
				email.setBody("Ti-am trimis. Speram sa ajunga (de data asta)");
			} else {
				email.setSubject("Order Received");
				email.setBody("Thank you for your order");
			}
			boolean success = context.send(email);
			if (success) break;
		}
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