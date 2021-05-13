package victor.training.patterns.behavioral.observer.invoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderWasInStockEvent;

@Service
@Slf4j
public class InvoiceService {
   @EventListener
//   @Order(2)
   public void generateInvoice(OrderWasInStockEvent event) {
      log.debug("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
