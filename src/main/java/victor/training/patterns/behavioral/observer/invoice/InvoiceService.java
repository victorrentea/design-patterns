package victor.training.patterns.behavioral.observer.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderInStockEvent;

@Service
@Slf4j
public class InvoiceService { // 5k LOC
   @EventListener
   public void generateInvoice(OrderInStockEvent event) {
      log.debug("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
