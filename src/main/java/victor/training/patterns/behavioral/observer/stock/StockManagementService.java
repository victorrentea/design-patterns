package victor.training.patterns.behavioral.observer.stock;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService {
   @EventListener
   @Order(10)
   public void checkStock(OrderPlacedEvent orderPlacedEvent) {
      System.out.println("Checking stock for products in order " + orderPlacedEvent.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
   }
}
