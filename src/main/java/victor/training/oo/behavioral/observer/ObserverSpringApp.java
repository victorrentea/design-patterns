package victor.training.oo.behavioral.observer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class ObserverSpringApp implements CommandLineRunner {
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
	public void run(String... args) throws Exception {
		publisher.publishEvent(new OrderPlaced(13));
		//afterTransaction.runInTransaction();
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
//		publisher.publishEvent(new GenerateInvoiceCommand(event.getOrderId()));
		publisher.publishEvent(new OrderInStockEvent(event.getOrderId()));
	}
}

@Data
class OrderInStockEvent {
	private final long orderId;
}



@Slf4j
@Service
class InvoiceService {

	@EventListener
	public void generateInvoice(OrderInStockEvent event) {
		log.info("Generating invoice for order id: " + event.getOrderId());
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}