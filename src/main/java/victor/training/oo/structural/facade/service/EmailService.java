package victor.training.oo.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Email;
import victor.training.oo.structural.facade.infra.EmailClient;
import victor.training.oo.structural.facade.repo.EmailRepository;

@Service
@RequiredArgsConstructor
public class EmailService {
   private final EmailRepository emailRepo;
   private final EmailClient emailClient;

   public void sendRegistrationEmail(String emailAddress) {
      System.out.println("Sending activation link via email to "+ emailAddress);
      sendEmail(emailAddress, "Welcome!", "You'll like it! Sincerely, Team");
   }
   public void sendStockEmail(String emailAddress) {
      System.out.println("Sending activation link via email to "+ emailAddress);
      sendEmail(emailAddress, "Orderu e in stock!", "Avem di tate");
   }

   private void sendEmail(String emailAddress, String subject, String body) {
      Email email = new Email();
      email.setFrom("noreply");
      email.setTo(emailAddress);
      email.setSubject(subject);
      email.setBody(body);

      if (!emailRepo.emailWasSentBefore(email.hashCode())) {
         emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
         emailRepo.saveSentEmail(email);
      }
   }
}
