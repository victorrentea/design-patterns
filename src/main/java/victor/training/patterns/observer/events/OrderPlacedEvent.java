package victor.training.patterns.observer.events;

import org.springframework.context.ApplicationEvent;

public class OrderPlacedEvent {
    private final Long orderId;

    public OrderPlacedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
