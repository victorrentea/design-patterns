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

	// TODO [1] also generate invoice
	// TODO [2] control the order
	// TODO [3] chain events
	// TODO [opt] Transaction-scoped events
	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		log.info("Halo!");
		publisher.publishEvent(new OrderPlaced(13));
		// afterTransaction.runInTransaction();
	}
}

@Data
class OrderPlaced {
	private final long orderId;
}

@Slf4j
@Service
class StockManagementService {
	@Autowired
	private ApplicationEventPublisher publisher;

	@EventListener
	public void handle(OrderPlaced event) {
		log.info("Checking stock for products in order " + event.getOrderId());
		log.info("If something goes wrong - throw an exception");
	}
}

@Slf4j
@Service
class InvoiceService {
	
	public void generateInvoice(long orderId) {
		log.info("Generating invoice for order id: " + orderId);
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}