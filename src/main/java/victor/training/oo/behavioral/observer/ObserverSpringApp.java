package victor.training.oo.behavioral.observer;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootApplication
public class ObserverSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(ObserverSpringApp.class, args);
	}
	
//	@Bean
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ObserverTransaction afterTransaction;

	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		placeOrder(13L);
		// afterTransaction.runInTransaction();
	}

	// order package =======
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	// TODO [1] also generate invoice
	// TODO [2] control the order
	// TODO [3] chain events
	// TODO [opt] Transaction-scoped events
	private void placeOrder(Long orderId) {
		log.debug("Halo!");

		OrderPlacedEvent event = new OrderPlacedEvent(orderId);
		eventPublisher.publishEvent(event);
	}
}

// --- event package
@Value
class OrderPlacedEvent {
	long orderId;
}
@Value
class OrderInStockEvent {
	long orderId;
}

/// ---- stock package
@Service
@Slf4j
class StockManagementService {
	@EventListener
	public OrderInStockEvent checkStock(OrderPlacedEvent event) {
		log.debug("Checking stock for products in order " + event.getOrderId());
		log.debug("If something goes wrong - throw an exception");
		return new OrderInStockEvent(event.getOrderId());
	}
}
/// ---- invoice package
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