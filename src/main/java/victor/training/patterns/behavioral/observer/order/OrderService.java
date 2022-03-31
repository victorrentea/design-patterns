package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.order.event.OrderPlacedEvent;

@Service
public class OrderService {//6K
   @Autowired
   private ApplicationEventPublisher eventPublisher;

   public void placeOrder(Long orderId) {
      System.out.println("Halo!");
      // logic
      // logic
      // logic
      // logic
      // stockService.checkStock
      // logic
      eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
   }
}
