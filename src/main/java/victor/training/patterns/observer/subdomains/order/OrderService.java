package victor.training.patterns.observer.subdomains.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import victor.training.patterns.observer.events.OrderPlacedEvent;
import victor.training.patterns.observer.subdomains.stock.StockManagementService;

@Service
public class OrderService {
//    @Autowired
//    private StockManagementService stockManagementService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void placeOrder(Long orderId) {
        System.out.println("Create the order!");
//        stockManagementService.checkStock(orderId);
        eventPublisher.publishEvent(new OrderPlacedEvent(orderId));

        System.out.println("BOTH placeOrder + " +
           "the 2 listeners NOW run in the SAME TRANSACTION+THREAD (thank you!!!) for ACID");
        System.out.println("I ❤️ stong consistency. I won't give up this unless you give me veeery good reasons:" +
                           "scalability, different language, available, => deploy separately....");
    }
}
