package victor.training.patterns.observer.subdomains.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.events.OrderInStockEvent;
import victor.training.patterns.observer.subdomains.events.OrderPlacedEvent;

@Slf4j
@Service
public class StockManagementService {

    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        checkStock(event.getOrderId());
    }

    public void checkStock(long orderId) {
        log.info("STOCK: Checking stock for products in order " + orderId);
        //
        applicationEventPublisher.publishEvent(new OrderInStockEvent(orderId));

    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
}
