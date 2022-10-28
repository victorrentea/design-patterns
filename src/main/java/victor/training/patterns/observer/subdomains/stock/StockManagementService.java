package victor.training.patterns.observer.subdomains.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.event.OrderInStockEvent;
import victor.training.patterns.observer.event.OrderPlacedEvent;

@Service
public class StockManagementService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void onOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {
        checkStock(orderPlacedEvent.orderId());
    }
    public void checkStock(long orderId) {
        System.out.println("STOCK: Checking stock for products in order " + orderId);
        eventPublisher.publishEvent(new OrderInStockEvent(orderId));
    }
}

