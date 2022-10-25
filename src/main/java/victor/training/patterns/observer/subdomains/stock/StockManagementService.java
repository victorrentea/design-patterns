package victor.training.patterns.observer.subdomains.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.events.OrderInStockEvent;
import victor.training.patterns.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener // tell srping to register this as a handler for that type in the signature
    public void onOrderPlaced(OrderPlacedEvent event) {
        checkStock(event.orderId());
        eventPublisher.publishEvent(new OrderInStockEvent(event.orderId()));
    }

    public void checkStock(long orderId) {
        System.out.println("Checking stock for products in order " + orderId);
        System.out.println("If something goes wrong - throw an exception");
    }
}
