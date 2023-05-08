package victor.training.patterns.observer.subdomains.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.events.OrderPlacedEvent;
import victor.training.patterns.observer.subdomains.invoice.InvoiceService;
import victor.training.patterns.observer.subdomains.stock.StockManagementService;

@Service
@Slf4j
public class OrderService {
//    @Autowired
//    private StockManagementService stockManagementService;
//    @Autowired
//    private InvoiceService invoiceService;

    public void placeOrder() {
        log.info("ORDER: Saving the order in the DB");
        Long orderId = 13L; // = pretend the inserted PK

//        stockManagementService.checkStock(orderId);
//        invoiceService.generateInvoice(orderId);
        // TODO call invoicing too
        eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
    }

    @Autowired
    private ApplicationEventPublisher eventPublisher;
}
