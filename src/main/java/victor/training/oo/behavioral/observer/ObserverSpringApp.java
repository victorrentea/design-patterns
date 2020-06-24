package victor.training.oo.behavioral.observer;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication
public class ObserverSpringApp {
   public static void main(String[] args) {
      SpringApplication.run(ObserverSpringApp.class, args);
   }

   // NU FACETI ASTA!
   @Bean
   public ApplicationEventMulticaster applicationEventMulticaster() {
      SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
      eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
      return eventMulticaster;
   }

   @Autowired
   private ApplicationEventPublisher publisher;

   @Autowired
   private ObserverTransaction afterTransaction;

   @Transactional
   @EventListener
   public void atStartup(ContextRefreshedEvent event) {
      placeOrder(13L);
      // afterTransaction.runInTransaction();
   }

   // TODO [1] also generate invoice
   // TODO [2] control the order
   // TODO [3] chain events
   // TODO [opt] Transaction-scoped events

   private void placeOrder(Long orderId) {
      log.debug("Halo!");
      publisher.publishEvent(new OrderPlacedEvent(orderId));
      log.debug("After");
//		invoiceService.generateInvoice(orderId);
   }
//	@Autowired
//	private StockManagementService stockManagementService;
//	@Autowired
//	private InvoiceService invoiceService;
}

// event package
@Value
class OrderPlacedEvent {
   long orderId;
//   long customerId;
//   long discountCouponId;
}

@Value
class OrderInStockEvent {
   long orderId;
}
// ------------



// "record" java 14
//record OrderPlacedEvent2(long orderId) {}
/// ------- izolare sociala
@Service
@Slf4j
class StockManagementService {
	@Autowired
	private ApplicationEventPublisher publisher;

   @EventListener
   public void checkStock(OrderPlacedEvent event) { // NOSONAR
      log.debug("Checking stock for products in order " + event.getOrderId());
      log.debug("If something goes wrong - throw an exception");
      if (true) {
         throw new IllegalStateException("N-am");
      }
		publisher.publishEvent(new OrderInStockEvent(event.getOrderId()));

//		publisher.publishEvent(new GenerateInvoiceCommand(event.getOrderId()));

//      return new OrderInStockEvent(event.getOrderId()); // idem behavior
   }
}

/// ------- izolare sociala
@Service
@Slf4j
class InvoiceService {
   @EventListener
   public void generateInvoice(OrderInStockEvent event) {
      log.debug("Generating invoice for order id: " + event.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}