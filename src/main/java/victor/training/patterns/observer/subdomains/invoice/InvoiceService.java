package victor.training.patterns.observer.subdomains.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.order.OrderPlacedEvent;
import victor.training.patterns.observer.subdomains.stock.OrderInStockEvent;

@Slf4j
@Service
public class InvoiceService {
    @EventListener
    public void onEvent(OrderInStockEvent event) {
        generateInvoice(event.orderId());
    }
    public void generateInvoice(long orderId) {
        log.info("INVOICE: Sending invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
