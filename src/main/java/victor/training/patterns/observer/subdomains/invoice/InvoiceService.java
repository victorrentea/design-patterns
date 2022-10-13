package victor.training.patterns.observer.subdomains.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.events.OrderPlacedAndWeHaveStockEvent;
import victor.training.patterns.observer.events.OrderPlacedEvent;

@Service
public class InvoiceService {
    @EventListener
    public void onOrderPlaced(OrderPlacedAndWeHaveStockEvent event) {
        generateInvoice(event.getOrderId());
    }
    public void generateInvoice(long orderId) {
        System.out.println("Generating invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
