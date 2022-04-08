package victor.training.patterns.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.invoice.InvoiceService;
import victor.training.patterns.observer.order.event.OrderPlacedEvent;
import victor.training.patterns.observer.stock.StockManagementService;

@Service
public class OrderService {
   @Autowired
   private StockManagementService stockManagementService;

   public void placeOrder(Long orderId) {
      System.out.println("Halo!");
//      stockManagementService.reserveStock(orderId);
//      invoiceService.generateInvoice(orderId);
      // TODO call invoicing too

//      try {
//         // biz code
//         eventPublisher.publishEvent("m-am conectat");
//      } catch (e) {
//         eventPublisher.publishEvent("io am murit");
//      }
      eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
   }

   @Autowired
   private ApplicationEventPublisher eventPublisher;
}
