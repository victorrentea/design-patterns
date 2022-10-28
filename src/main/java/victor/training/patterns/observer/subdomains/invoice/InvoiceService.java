package victor.training.patterns.observer.subdomains.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.event.OrderInStockEvent;
import victor.training.patterns.observer.event.OrderPlacedEvent;

@Slf4j
@Service
public class InvoiceService {

    @EventListener
    public void onOrderPlacedEvent(OrderInStockEvent orderPlacedEvent) {
        generateInvoice(orderPlacedEvent.orderId());
    }
    public void generateInvoice(long orderId) {
        log.info("INVOICE: Sending invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
