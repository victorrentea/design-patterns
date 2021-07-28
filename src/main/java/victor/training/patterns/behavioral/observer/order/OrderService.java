package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class OrderService {
   @Autowired
   private ApplicationEventPublisher publisher;

   public void placeOrder(Long orderId) {
//      orderRepo.save(order);
//      orderId = order.getId();
      System.out.println("Persist order!");
      OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(orderId);
      publisher.publishEvent(orderPlacedEvent);
//      invoiceService.generateInvoice(orderId);

   }
}
