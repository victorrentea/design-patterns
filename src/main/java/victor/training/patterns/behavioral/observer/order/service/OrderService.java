package victor.training.patterns.behavioral.observer.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
@Slf4j
public class OrderService {

   @Autowired
   ApplicationEventPublisher publisher;

   public void placeOrder(Long orderId) {
      log.debug("Halo!");
      publisher.publishEvent(new OrderPlacedEvent(orderId));
//      invoiceService.generateInvoice(orderId);
   }
}
