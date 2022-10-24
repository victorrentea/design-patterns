package victor.training.patterns.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.facade.entity.Email;
import victor.training.patterns.facade.infra.EmailClient;
import victor.training.patterns.facade.repo.EmailRepo;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final EmailClient emailClient;
    private final EmailRepo emailRepo;
    public void sendRegistrationEmail(String emailAddress) {
        System.out.println("Sending activation link via email to " + emailAddress);
        Email email = new Email();
        email.setFrom("noreply");
        email.setTo(emailAddress);
        email.setSubject("Welcome!");
        email.setBody("You'll like it! Sincerely, Team");

        if (!emailRepo.emailWasSentBefore(email.hashCode())) {
            emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
            emailRepo.saveSentEmail(email);
        }
    }
}
