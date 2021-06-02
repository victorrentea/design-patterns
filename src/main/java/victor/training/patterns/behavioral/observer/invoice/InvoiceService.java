package victor.training.patterns.behavioral.observer.invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
public class InvoiceService {
   private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
   @EventListener
   public void generateInvoice(OrderWasInStockEvent orderPlacedEvent) {
      log.debug("Generating invoice for order id: " + orderPlacedEvent.getOrderId());
      // TODO what if...

   }
}
