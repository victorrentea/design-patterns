package victor.training.patterns.behavioral.observer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Random;

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
	private ObserverTransaction afterTransaction;

	@Autowired
	private OrderService orderService;

	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		orderService.placeOrder(new Random().nextLong());
		// afterTransaction.runInTransaction();
	}


}

@Service
	// ~microservice
class OrderService {
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	//	@Autowired
//	private StockManagementService stockManagementService;
//	@Autowired
//	private InvoiceService invoiceService;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	//	@Transactional
	public void placeOrder(Long orderId) {
		log.info("Halo!");
//		stockManagementService.checkStock(orderId);
//		invoiceService.generateInvoice(orderId);
		// TODO call invoicing too
		applicationEventPublisher.publishEvent(new OrderPlacedEvent(orderId));
	}
}

class OrderPlacedEvent {
	private final long orderId;

	OrderPlacedEvent(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}
}

@Service
	// ~microservice
class StockManagementService {
	private static final Logger log = LoggerFactory.getLogger(StockManagementService.class);
	@Autowired
	ApplicationEventPublisher publisher;

	@EventListener
	public void checkStock(OrderPlacedEvent event) {
		log.info("Checking stock for products in order " + event.getOrderId());
		log.info("If something goes wrong - throw an exception");
		publisher.publishEvent(new OrderWasInStock(event.getOrderId())); // event: many receivers; in the past; emitter decides the schema of event
//		publisher.publishEvent(new IssueInvoice(event.getOrderId())); // command: 1 receiver; future; the executor(receiver) decides the parameter
	}
}

class OrderWasInStock { // event
	private final long orderId;

	OrderWasInStock(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}
}

@Service
class InvoiceService { // ~microservice
	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@EventListener
	public void generateInvoice(OrderWasInStock event) {
		log.info("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	}
}