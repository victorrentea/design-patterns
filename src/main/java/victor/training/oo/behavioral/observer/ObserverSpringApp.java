package victor.training.oo.behavioral.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class ObserverSpringApp implements CommandLineRunner {
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

	// TODO [1] also generate invoice
	// TODO [2] control the order
	// TODO [3] chain events
	// TODO [opt] Transaction-scoped events
//	@Transactional
	public void run(String... args) throws Exception {
		publisher.publishEvent(new OrderPlaced(13));
		//afterTransaction.runInTransaction();
		System.out.println("COMMIT");
	}
}

@Data
class OrderPlaced {
	private final long orderId;
}

// over the hills and far away

@Slf4j
@Service
class StockManagementService {
	@EventListener
	public OrderInStock handle(OrderPlaced event) {
		log.info("Checking stock for products in order " + event.getOrderId());
		log.info("If something goes wrong - throw an exception");
		if (true) throw new IllegalArgumentException("EU");
		return new OrderInStock(event.getOrderId());
	}
}
//over the hills and far away

@Data
class OrderInStock {
	private final long orderId;
}

@Slf4j
@Service
class InvoiceService {
	@EventListener
	public void generateInvoice(OrderInStock event) {
		log.info("Generating invoice for order id: " + event.getOrderId()); // TODO get orderId
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}