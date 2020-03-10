package victor.training.oo.behavioral.observer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    public void placeOrder(ContextRefreshedEvent event) {
        log.info("Halo!");
        publisher.publishEvent(new OrderPlaced(13));
        // afterTransaction.runInTransaction();
    }
}

@Data
class OrderPlaced {
    private final long orderId;
}

// mari si tari
@Slf4j
@Service
@RequiredArgsConstructor
class StockManagementService {
    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener
    public void handle(OrderPlaced orderPlaced) {
        log.info("Checking stock for products in order " + orderPlaced.getOrderId());
        log.info("If something goes wrong - throw an exception");
        publisher.publishEvent(new OrderInStock(orderPlaced.getOrderId()));
    }
}

@Data
class OrderInStock {
    private final long orderId;
}

@Slf4j
@Service
class InvoiceService {
    @EventListener
    public void generateInvoice(OrderInStock orderInStock) {
        log.info("Generating invoice for order id: " + orderInStock.getOrderId());
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}