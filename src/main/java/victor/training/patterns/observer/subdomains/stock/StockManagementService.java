package victor.training.patterns.observer.subdomains.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.order.OrderPlacedEvent;

@Service
@Slf4j
public class StockManagementService {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void onEvent(OrderPlacedEvent event) {
        checkStock(event.orderId());
    }
    public void checkStock(long orderId) {
        log.info("STOCK: Checking stock for products in order " + orderId);
        boolean am = true;
        if(am) {
            applicationEventPublisher.publishEvent(new OrderInStockEvent(orderId));
        } else {
            // <-- SAGA PATTERD (dupa-masa)
        }
    }
}
