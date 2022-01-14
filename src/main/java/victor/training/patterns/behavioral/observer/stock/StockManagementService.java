package victor.training.patterns.behavioral.observer.stock;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService { // 1.500
   @EventListener
   @Order(10) // global coupling point
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
   }
}
