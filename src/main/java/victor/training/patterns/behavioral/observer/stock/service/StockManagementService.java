package victor.training.patterns.behavioral.observer.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
@Slf4j
public class StockManagementService {
   @Autowired
   ApplicationEventPublisher publisher;

   @EventListener
//   @Order(1)
   public void checkStock(OrderPlacedEvent event) {
      log.debug("Checking stock for products in order " + event.getOrderId());
      log.debug("If something goes wrong - throw an exception");
//      throw new IllegalArgumentException();
      publisher.publishEvent(new OrderWasInStockEvent(event.getOrderId()));
   }
}
