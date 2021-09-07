package victor.training.patterns.behavioral.observer.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
@Slf4j
public class OrderService {
   //   @Autowired
//   private StockManagementService stockManagementService;
//
//   @Autowired
//   private InvoiceService invoiceService;
   @Autowired
   ApplicationEventPublisher publisher;

   @Transactional
   public void placeOrder(Long orderId) {
      log.debug("Halo!");
      //
//      repo.save();
//      stockManagementService.checkStock(orderId);
//      //
      //entity Invoice invoice = invoiceService.generateInvoice(orderId);


      publisher.publishEvent(new OrderPlacedEvent(orderId));
   }
}
