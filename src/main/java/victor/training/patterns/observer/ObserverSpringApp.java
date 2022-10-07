package victor.training.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@EventListener(ContextRefreshedEvent.class)
	public void atStartup() {
		orderService.placeOrder(new Random().nextLong());
		// afterTransaction.runInTransaction();
	}
}

@Service
class OrderService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

@Transactional
	public void placeOrder(Long orderId) {
		System.out.println("Halo!");
		eventPublisher.publishEvent(new OrderPlacedEvent(orderId)); // in mem blocking call right now
		System.out.println("END");
	}
}

record OrderPlacedEvent(long orderId) {}
record InvoiceGeneratedEvent(long orderId) {}

@Service
class InvoiceService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@EventListener
	public void generateInvoice(OrderPlacedEvent event) {
		callHierarchy(event);
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
		eventPublisher.publishEvent(new InvoiceGeneratedEvent(event.orderId()));
	}

	private static void callHierarchy(OrderPlacedEvent event) {
		System.out.println("Generating invoice for order id: " + event.orderId());
	}
}

@Service
class AuditService {
	@EventListener
	public void audit(InvoiceGeneratedEvent event) {
		System.out.println("Audit " + event.orderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	}
}