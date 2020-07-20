package victor.training.oo.behavioral.observer;

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
	private void placeOrder(Long orderId) {
		System.out.println("Halo!");
		stockManagementService.checkStock(orderId);
		// TODO call invoicing too
		invoiceService.generateInvoice(orderId);
	}
	@Autowired
	private StockManagementService stockManagementService;
	@Autowired
	private InvoiceService invoiceService;
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