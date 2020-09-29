package victor.training.oo.behavioral.observer;

import lombok.Data;
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

	// TODO [1] also generate invoice
	// TODO [2] control the order
	// TODO [3] chain events
	// TODO [opt] Transaction-scoped events
	// ====== orders. ==========
	private void placeOrder(Long orderId) {
		System.out.println("Halo!");
//		invoiceService.generateInvoice(orderId); <---
		eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
//		eventPublisher.publishEvent(new InvoiceEvent(orderId));
	}
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	private InvoiceService invoiceService;
}

// ==================== events.
@Data
class OrderPlacedEvent {
	private final long orderId;
}
@Data
class GenerateInvoiceCommand { //Command Pattern
//class OrderInStockEvent { // Observer Pattern
	private final long orderId;
}
@Data
class GenerateInvoiceCommand2 { //Command Pattern
//class OrderInStockEvent { // Observer Pattern
	private final long orderId;
}
// ================= stock. management ==================
@Service
class OrderAuditService {
	@EventListener
	public void checkStock(OrderPlacedEvent event) {
		System.out.println("Logging order  " + event.getOrderId());
	}
}
@Service
class StockManagementService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@EventListener
	public void checkStock(OrderPlacedEvent event) {
		System.out.println("Checking stock for products in order " + event.getOrderId());
		System.out.println("If something goes wrong - throw an exception");
		eventPublisher.publishEvent(new GenerateInvoiceCommand(event.getOrderId()));
		eventPublisher.publishEvent(new GenerateInvoiceCommand2(event.getOrderId()));
	}
}
// =============== invoicing. ======================
@Service
class InvoiceService {
	@EventListener
	public void generateInvoice(GenerateInvoiceCommand event) {
		System.out.println("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}
@Service
class InvoiceService2 {
	@EventListener
	public void generateInvoice(GenerateInvoiceCommand event) {
		System.out.println("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	}
}