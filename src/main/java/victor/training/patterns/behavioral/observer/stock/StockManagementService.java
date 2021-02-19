package victor.training.patterns.behavioral.observer.stock;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.CheckStockCommand;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
public class StockManagementService {
   @EventListener
   public OrderWasInStockEvent checkStock(CheckStockCommand event) {
      long orderId = event.getOrderId();
      System.out.println("Checking stock for products in order " + orderId);
      System.out.println("If something goes wrong - throw an exception");
      return new OrderWasInStockEvent(orderId);
   }
}
