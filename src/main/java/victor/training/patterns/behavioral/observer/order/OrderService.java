package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class OrderService {
   //   @Autowired
//   private StockManagementService stockManagementService;
   @Autowired
   private ApplicationEventPublisher eventPublisher;

   @Transactional
   public void placeOrder(Long orderId) {
      System.out.println("Persist the order!");
//      stockManagementService.checkStock(orderId);
      eventPublisher.publishEvent(new OrderPlacedEvent(orderId)); // block
      System.out.println("Pana aici au rulat toate Listenerele in threadl meu");
   }
}
