package victor.training.patterns.behavioral.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.order.event.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.stock.event.StockReservedEvent;

@Service
public class StockManagementService { // 12K

   @Autowired
   private ApplicationEventPublisher eventPublisher;

   @EventListener
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      eventPublisher.publishEvent(new StockReservedEvent(event.getOrderId()));
   }
}
