package victor.training.patterns.behavioral.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.events.StockReservedEvent;

@Service
public class StockManagementService { // 1.500
   @Autowired
   private ApplicationEventPublisher publisher;

   @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//@Transactional
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      publisher.publishEvent(new StockReservedEvent(event.getOrderId()));
   }
}

// command pattern compare with observer
// choreography vs orchestration
// saga
