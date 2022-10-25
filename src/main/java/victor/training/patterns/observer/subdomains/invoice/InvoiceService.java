package victor.training.patterns.observer.subdomains.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceService {
    public void generateInvoice(long orderId) {
        log.info("INVOICE: Sending invoice for order id: " + orderId);
        // TODO what if...
        // throw new RuntimeException("thrown from generate invoice");
    }
}
