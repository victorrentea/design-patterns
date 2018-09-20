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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

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

	public void run(String... args) throws Exception {
		publisher.publishEvent(new OrderPlaced(13)); // SOLUTION
		afterTransaction.runInTransaction();
	}
}

@Data
class OrderPlaced {
	public final long orderId;
}

@Data
class OrderInStock {
	public final long orderId;
}

@Slf4j
@Service
class StockManagementService {
	@Autowired
	private ApplicationEventPublisher publisher;

	@EventListener
	// @Order(1) // bad idea // SOLUTION
	// public void handle(OrderPlaced event) {// INITIAL
	public OrderInStock handle(OrderPlaced event) { // SOLUTION
		log.info("Checking stock for products in order " + event.orderId);
		// publisher.publishEvent(new OrderInStock(event.orderId)); // bad? alternative // SOLUTION
		return new OrderInStock(event.orderId);// SOLUTION
	}
}

@Slf4j
@Service
class InvoiceService {
	@EventListener // SOLUTION (
	// @Order(100) // bad idea // SOLUTION
	public void handle(OrderPlaced event) {
		// public void handle(OrderInStock event) {
		log.info("Generating invoice for order " + event.orderId);
		new RuntimeException("dummy").printStackTrace(System.out);
	} // SOLUTION )
}