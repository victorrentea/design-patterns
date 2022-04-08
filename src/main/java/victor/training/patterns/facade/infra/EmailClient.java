package victor.training.patterns.facade.infra;

import org.springframework.stereotype.Service;

// We pretend this is an external API we have to use
@Service
public class EmailClient {
	
	public void sendEmail(String from, String to, String subject, String message) {
		// implementation goes here
	}
}
