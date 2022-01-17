package victor.training.patterns.behavioral.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@Slf4j
@Service
public class Barman {
   public Beer pourBeer() {
      log.debug("Pouring Beer...");
      sleepq(1000); // REST call
      return new Beer();
   }

   public Vodka pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000); // SOAP /wsdl, device, DB
      return new Vodka();
   }
}
