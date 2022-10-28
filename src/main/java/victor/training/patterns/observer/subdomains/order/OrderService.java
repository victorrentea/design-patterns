package victor.training.patterns.observer.subdomains.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.event.OrderPlacedEvent;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void placeOrder() {
        log.info("ORDER: Saving the order in the DB");
        Long orderId = 13L; // = pretend the inserted PK

        eventPublisher.publishEvent(new OrderPlacedEvent(orderId));
        // TODO call invoicing too
    }
}
