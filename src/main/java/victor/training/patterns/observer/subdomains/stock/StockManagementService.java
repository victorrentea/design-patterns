package victor.training.patterns.observer.subdomains.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.events.OrderPlacedAndWeHaveStockEvent;
import victor.training.patterns.observer.events.OrderPlacedEvent;

@Service
public class StockManagementService {
    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        checkStock(event.getOrderId());
    }
    public void checkStock(long orderId) {
        System.out.println("Checking stock for products in order " + orderId);
        System.out.println("If something goes wrong - throw an exception");
        eventPublisher.publishEvent(new OrderPlacedAndWeHaveStockEvent(orderId));
    }

    @Autowired
    private ApplicationEventPublisher eventPublisher;
}
