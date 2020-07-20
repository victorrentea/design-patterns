package victor.training.oo.behavioral.observer;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j
@SpringBootApplication
public class ObserverSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(ObserverSpringApp.class, args);
	}
	
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
//		stockManagementService.checkStock(orderId);
		publisher.publishEvent(new OrderPlacedEvent(orderId));
	}
	@Autowired
	private StockManagementService stockManagementService;
}
@Value
class OrderPlacedEvent {
	long orderId;
}

@Service
class StockManagementService {
	private static final Logger log = LoggerFactory.getLogger(StockManagementService.class);
	@Autowired
	private ApplicationEventPublisher publisher;
	@EventListener
	public void checkStock(OrderPlacedEvent event) {
		log.debug("Checking stock for products in order " + event.getOrderId());
		//call WS 5 min rest.geT() --> StockCheck: IN_PROGRESS

		publisher.publishEvent(new OrderInStockEvent(event.getOrderId()));
		log.debug("If something goes wrong - throw an exception");
		throw new IllegalArgumentException("N-am stoc");
	}
}

@Value
class OrderInStockEvent {
	long orderId;
}

@Service
class InvoiceService {
	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
	@EventListener
	public void generateInvoice(OrderInStockEvent orderPlacedEvent) {
		log.debug("Generating invoice for order id: " + orderPlacedEvent.getOrderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}