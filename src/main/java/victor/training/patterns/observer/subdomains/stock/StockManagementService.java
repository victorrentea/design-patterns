package victor.training.patterns.observer.subdomains.stock;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.order.OrderPlacedEvent;

@Service
public class StockManagementService {
    @EventListener
    public void onEvent(OrderPlacedEvent event) {
        checkStock(event.orderId());
    }
    public void checkStock(long orderId) {
        System.out.println("STOCK: Checking stock for products in order " + orderId);
    }
}
