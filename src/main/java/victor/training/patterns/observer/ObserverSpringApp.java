package victor.training.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.events.OrderPlacedEvent;

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
class OrderService { // 4k lines of code
	@Autowired
	private StockManagementService stockManagementService;
//	@Autowired
//	private InvoiceService invoiceService; // coupling together 2 HUGE unrelated modules.
@Autowired
private ApplicationEventPublisher eventPublisher;

	public void placeOrder(Long orderId) {
		System.out.println("Halo!");
		stockManagementService.checkStock(orderId);
		// TODO call invoicing too
//		invoiceService.generateInvoice(orderId);
		eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
		// here all the listeners have already RAN
	}
}

@Service
class StockManagementService { // 3000 lines opf code
	public void checkStock(long orderId) {
		System.out.println("Checking stock for products in order " + orderId);
		System.out.println("If something goes wrong - throw an exception");
	}
}

@Service
class InvoiceService { // 5000 LOC

//	@Async // horror
	@EventListener
	public void generateInvoice(OrderPlacedEvent event) {
		System.out.println("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
//		 throw new RuntimeException("thrown from generate invoice");
	} 
}