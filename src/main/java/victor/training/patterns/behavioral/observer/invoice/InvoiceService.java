package victor.training.patterns.behavioral.observer.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
public class InvoiceService {
   @EventListener
   public void generateInvoice(OrderWasInStockEvent orderPlacedEvent) {
      System.out.println("Generating invoice for order id: " + orderPlacedEvent.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
