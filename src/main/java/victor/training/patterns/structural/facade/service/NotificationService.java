package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.EmailRepo;

@RequiredArgsConstructor
@Service
public class NotificationService {
   private final EmailClient emailClient;
   private final EmailRepo emailRepo;


   public void sendRegistrationEmail(String emailAddress) {
      System.out.println("Sending activation link via email to " + emailAddress);

      sendEmail(emailAddress, "Welcome!", "You'll like it! Sincerely, Team");
   }

   public void sendEmail(String emailAddress, String subject, String body) {
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

   public void sendStockCheckedEmail(String email) {
      sendEmail(email, "Your product is in stock! Hooray!", "Incredible!");
   }
}
