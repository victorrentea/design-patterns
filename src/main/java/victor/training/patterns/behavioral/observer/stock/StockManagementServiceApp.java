package victor.training.patterns.behavioral.observer.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@EnableBinding(StockQueues.class)
@Service
@SpringBootApplication
public class StockManagementServiceApp {
   private static final Logger log = LoggerFactory.getLogger(StockManagementServiceApp.class);

   public static void main(String[] args) {
      SpringApplication.run(StockManagementServiceApp.class, args);
   }
//   @Autowired
//   private ApplicationEventPublisher eventPublisher;

   @ServiceActivator(inputChannel = "ope-in")
   public void checkStock(String orderIdStr) {
      log.debug("Checking stock for produ INSERT cts in order " + orderIdStr);
//      log.debug("If something goes wrong - throw an exception");
////      if (true) throw new RuntimeException("out of stock");
//      eventPublisher.publishEvent(new OrderWasInStockEvent(orderIdStr));
   }
}

interface StockQueues {
   @Input("ope-in")
   SubscribableChannel orderPlacedEventInput();
}
