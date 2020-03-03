package victor.training.oo.behavioral.template;

import lombok.Data;

import java.util.Random;

public class TemplateSpringApp {
    public static void main(String[] args) {
        new OrderReceivedEmailSender().sendEmail("a@b.com");

        /// TODO sendOrderShippedEmail la fel ca la send order received

		new OrderShippedEmailSender().sendEmail("a@b.com");
    }
}

abstract class AbstractEmailSender {
    public void sendEmail(String emailAddress) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            email.setTo(emailAddress);
            fillEmail(email);
            boolean success = context.send(email);
            if (success) break;
        }
    }
    protected abstract void fillEmail(Email email);
}

class OrderReceivedEmailSender extends AbstractEmailSender {
    @Override
    protected void fillEmail(Email email) {
        email.setSubject("Order Received");
        email.setBody("Thank you for your order");
    }
}
class OrderShippedEmailSender extends AbstractEmailSender {
    @Override
    protected void fillEmail(Email email) {
        email.setSubject("Order Shipped");
        email.setBody("Ti-am trimis. Speram sa ajunga (de data asta)");
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