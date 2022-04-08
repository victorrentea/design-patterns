package victor.training.patterns.observer.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.stock.event.StockReservedEvent;

@Service
public class InvoiceService {
   @EventListener
   @Retryable
   public void generateInvoice(StockReservedEvent event) {
      System.out.println("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
