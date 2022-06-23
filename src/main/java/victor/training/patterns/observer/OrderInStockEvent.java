package victor.training.patterns.observer;

public class OrderInStockEvent {
    private final Long orderId;

    public OrderInStockEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
