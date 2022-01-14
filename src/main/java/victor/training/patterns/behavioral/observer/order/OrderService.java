package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class OrderService { // 2k
   @Autowired
   private ApplicationEventPublisher publisher;

   public void placeOrder(Long orderId) {
      System.out.println("persist the order!");
      publisher.publishEvent(new OrderPlacedEvent(orderId));
//      stockManagementService.checkStock(orderId);
//      invoiceService.generateInvoice(orderId);
   }
}
