package victor.training.patterns.behavioral.observer.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.StockReservedForOrderEvent;

@Service
public class InvoiceService { // INvoice Generator : 35.000 LOC
   @EventListener
   public void generateInvoice(StockReservedForOrderEvent event) {
      System.out.println("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
