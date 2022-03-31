package victor.training.patterns.behavioral.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.order.event.OrderPlacedEvent;

@Service
public class AuditServic {
   @EventListener
   public void auditOrderPlaced(OrderPlacedEvent event) {
      System.out.println("Audit insert event");
   }
}
