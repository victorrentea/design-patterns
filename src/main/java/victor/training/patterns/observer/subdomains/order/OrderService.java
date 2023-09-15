package victor.training.patterns.observer.subdomains.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    //    @Autowired
//    private StockManagementService stockManagementService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void placeOrder() {
        log.info("ORDER: Saving the order in the DB");
        Long orderId = 13L; // = pretend the inserted PK


//        stockManagementService.checkStock(orderId); // ma irita caci trece o granita pe care vreau s-o tai
        eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
        // TODO call invoicing too
    }
}
