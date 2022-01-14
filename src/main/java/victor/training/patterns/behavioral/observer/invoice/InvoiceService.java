package victor.training.patterns.behavioral.observer.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.StockReservedEvent;

@Service
public class InvoiceService { // 35.000
   @EventListener
   public void generateInvoice(StockReservedEvent event) {
      System.out.println("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
