package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.EmailRepository;

@RequiredArgsConstructor
public class EmailService {
   private final EmailRepository emailRepo;
   private final EmailClient emailClient;

   public void sendEmail(String emailAddress, String subject, String body) {
      System.out.println("Sending activation link via email to "+ emailAddress);
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
