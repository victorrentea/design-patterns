package victor.training.patterns.behavioral.observer.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.events.StockReservedForOrderEvent;

@Service
public class StockManagementService { // 4k
   @Autowired
   ApplicationEventPublisher publisher;

//   public StockManagementService() {
//      GLobalEventRegistry.registerListener(OrderPlacedEvent.class, this::checkStock);
//   }

   @EventListener
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
//		if (!amStoc) {
//			throw new IllegalArgumentException();
//		}
      boolean amPeStoc = true;
      if (amPeStoc) {
         // in microservicii: rezerv stocul 5 min pentru orderul X
         // blochez stocul pentru Tx curenta
//         LOCK TABLE STOCK
         // SELECT FOR UPDATE ID fROM STOCK WHERE PRODUCT_ID = ?
         // Command ordona cuiva sa faca ceva

//         publisher(new GenerateInvoiceOrderEvent(event.getOrderId()));
         publisher.publishEvent(new StockReservedForOrderEvent(event.getOrderId()));
      }
   }
}
