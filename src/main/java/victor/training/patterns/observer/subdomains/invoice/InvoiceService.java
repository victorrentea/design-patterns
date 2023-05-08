package victor.training.patterns.observer.subdomains.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.events.OrderInStockEvent;
import victor.training.patterns.observer.subdomains.events.OrderPlacedEvent;

@Slf4j
@Service
public class InvoiceService {

    @EventListener
    public void onOrderPlaced(OrderInStockEvent event) {
        generateInvoice(event.getOrderId());
    }

    public void generateInvoice(long orderId) {
        log.info("INVOICE: Sending invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
