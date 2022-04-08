package victor.training.patterns.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.order.event.OrderPlacedEvent;
import victor.training.patterns.observer.stock.event.StockReservedEvent;

@Service
public class StockManagementService {
   @EventListener
   public void reserveStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      eventPublisher.publishEvent(new StockReservedEvent(event.getOrderId()));
   }

   @Autowired
   private ApplicationEventPublisher eventPublisher;
}
