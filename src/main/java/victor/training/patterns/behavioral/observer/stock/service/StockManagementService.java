package victor.training.patterns.behavioral.observer.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(StockQueues.class)
@SpringBootApplication
@Slf4j
public class StockManagementService {
   public static void main(String[] args) {
      SpringApplication.run(StockManagementService.class, "--server.port=8081");
   }

   @Autowired
   ApplicationEventPublisher publisher;

   @ServiceActivator(inputChannel = "q1in")
   public void checkStock(String orderId) {
      log.debug("Checking stock for products in order " + orderId);
      log.debug("If something goes wrong - throw an exception");
//      publisher.publishEvent(new OrderWasInStockEvent(event.getOrderId()));
   }
}

interface StockQueues {
   @Input("q1in")
   SubscribableChannel orderPlacedEventQueue();

   @Output("q2out")
   MessageChannel orderInStockEventQueue();
}
