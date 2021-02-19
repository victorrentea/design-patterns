package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.CheckStockCommand;

@Service
public class OrderService {

   @Autowired
   ApplicationEventPublisher eventPublisher;

   // TODO [1] also generate invoice
   // TODO [2] control the order
   // TODO [3] chain events
   // TODO [opt] Transaction-scoped events
   public void placeOrder(Long orderId) {
      System.out.println("Halo!");
      eventPublisher.publishEvent(new CheckStockCommand(orderId));

//      boolean ok = stockAdapter.hasStock(orderId); // REST call pentru citeste
//      boolean ok = stockAdapter.reserveStock(orderId); // QUEUE message pentru ca modifica

//
   }

}
