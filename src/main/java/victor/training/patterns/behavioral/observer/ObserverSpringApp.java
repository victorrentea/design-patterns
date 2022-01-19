package victor.training.patterns.behavioral.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
public class ObserverSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(ObserverSpringApp.class, args);
	}

	@Autowired
	private OrderService orderService;

	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		orderService.placeOrder(new Random().nextLong());
		// afterTransaction.runInTransaction();
	}


}

@Service
class OrderService {
	@Autowired
	private StockManagementService stockManagementService;

	public void placeOrder(Long orderId) {
		System.out.println("Halo!");
		stockManagementService.checkStock(orderId);
		// TODO call invoicing too
	}
}

@Service
class StockManagementService {
	public void checkStock(long orderId) {
		System.out.println("Checking stock for products in order " + orderId);
		System.out.println("If something goes wrong - throw an exception");
	}
}

@Service
class InvoiceService {
	public void generateInvoice(long orderId) {
		System.out.println("Generating invoice for order id: " + orderId);
		// TODO what if...
		// throw new RuntimeException("thrown from generate invoice");
	} 
}