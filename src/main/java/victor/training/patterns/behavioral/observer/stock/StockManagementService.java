package victor.training.patterns.behavioral.observer.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import victor.training.patterns.behavioral.observer.events.OrderInStockEvent;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Slf4j
@Service
public class StockManagementService {
   boolean hasStock = true;

   @Autowired
   ApplicationEventPublisher publisher;

   @TransactionalEventListener
   @Transactional
//   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void checkStock(OrderPlacedEvent event) {
      log.debug("Checking stock for products in order " + event.getOrderId());
      log.debug("If something goes wrong - throw an exception");
      if (hasStock) {
         publisher.publishEvent(new OrderInStockEvent(event.getOrderId()));
      } else {
         throw new RuntimeException("Out of stock");
      }
   }
}
