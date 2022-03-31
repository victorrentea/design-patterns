package victor.training.patterns.behavioral.observer.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.order.event.OrderPlacedEvent;
import victor.training.patterns.behavioral.observer.stock.event.StockReservedEvent;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockManagementService { // 12K

   private final ApplicationEventPublisher eventPublisher;


   @EventListener
   public void checkStock(OrderPlacedEvent event) {
      System.out.println("Checking stock for products in order " + event.getOrderId());
      System.out.println("If something goes wrong - throw an exception");
      log.debug("ceva");
      eventPublisher.publishEvent(new StockReservedEvent(event.getOrderId()));
   }

}
