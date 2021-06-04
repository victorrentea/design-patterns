package victor.training.patterns.behavioral.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
public class StockManagementService {
   @Autowired
   private ApplicationEventPublisher eventPublisher;

   @EventListener
   public void checkStock(OrderPlacedEvent orderPlacedEvent) {
      System.out.println("Checking stock for products in order " + orderPlacedEvent.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      eventPublisher.publishEvent(new OrderWasInStockEvent(orderPlacedEvent.getOrderId()));
//      eventPublisher.publishEvent(new GenerateInvoiceCommand(orderPlacedEvent.getOrderId()));
   }
}
