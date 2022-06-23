package victor.training.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
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
	private ApplicationEventPublisher eventPublisher;

	public void placeOrder(Long orderId) {
		System.out.println("Halo!");
		eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
	}
}

@Service
class StockManagementService { // 3000 lines opf code
	@EventListener
	@Order(1)
	public void checkStock(OrderPlacedEvent event) {
		System.out.println("Checking stock for products in order " + event.getOrderId());
		System.out.println("If something goes wrong - throw an exception");
	}
}

@Service
class InvoiceService { // 5000 LOC

//	@Async // horror
	@EventListener
	@Order(2)
	public void generateInvoice(OrderPlacedEvent event) {
		System.out.println("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
//		 throw new RuntimeException("thrown from generate invoice");
	} 
}