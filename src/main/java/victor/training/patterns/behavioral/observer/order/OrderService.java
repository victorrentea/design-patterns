package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class OrderService { // 2k
   @Autowired
   private ApplicationEventPublisher eventPublisher;


   @Transactional
   public void placeOrder(Long orderId) {
      System.out.println("Halo!");
//      stockManagementService.checkStock(orderId);
//      invoiceService.generateInvoice(orderId);

      eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
      System.out.println("GATA FLUXUL");
      // TODO tau call invoicing too sa generezi factura
   }
}
