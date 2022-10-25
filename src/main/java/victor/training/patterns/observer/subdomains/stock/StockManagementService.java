package victor.training.patterns.observer.subdomains.stock;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService {

    @EventListener // tell srping to register this as a handler for that type in the signature
    public void onOrderPlaced(OrderPlacedEvent event) {
        checkStock(event.orderId());
    }

    public void checkStock(long orderId) {
        System.out.println("Checking stock for products in order " + orderId);
        System.out.println("If something goes wrong - throw an exception");
    }
}
