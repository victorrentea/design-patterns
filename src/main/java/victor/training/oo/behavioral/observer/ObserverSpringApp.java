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
	public void run(String... args) throws Exception {
		publisher.publishEvent(new OrderPlaced(13));
		//afterTransaction.runInTransaction();
	}
}

interface Cat1 {
	long getOrderId();
}

@Data
class OrderPlaced implements Cat1{
	public final long orderId;
}
@Data
class OrderChecked implements Cat1{
	public final long orderId;
}

@Slf4j
@Service
class StockManagementService {
	
	@EventListener
	public void stuff(Cat1 cat) {
		log.info("Does this work? "  + cat);
		
	}

	@EventListener
	public OrderChecked handle(OrderPlaced event) { 
		log.info("Checking stock for products in order " + event.orderId);
		log.info("If something goes wrong - throw an exception");
		return new OrderChecked(13);
	}
}

@Slf4j
@Service
class InvoiceService {
	@EventListener
	public void handleASDAS(OrderChecked event) {
		log.info("Generating invoice for order " + event.orderId);
		new RuntimeException("thrown from generate invoice").printStackTrace(System.out);
	} 
}