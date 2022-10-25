package victor.training.patterns.observer.subdomains.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import victor.training.patterns.observer.events.OrderPlacedEvent;

@Service
public class InvoiceService {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // tell srping to register this as a handler for that type in the signature
//    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        generateInvoice(event.orderId());
    }
    public void generateInvoice(long orderId) {
        System.out.println("Generating invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
