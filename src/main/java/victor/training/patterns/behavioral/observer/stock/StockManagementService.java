package victor.training.patterns.behavioral.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderInStockEvent;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService {
   @Autowired
   private ApplicationEventPublisher publisher;

   //   MessageSender  --- Pe cozi
   @EventListener
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      publisher.publishEvent(new OrderInStockEvent(event.getOrderId()));
   }
}
