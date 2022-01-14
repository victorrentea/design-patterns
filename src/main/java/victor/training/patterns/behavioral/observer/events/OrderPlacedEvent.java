package victor.training.patterns.behavioral.observer.events;

public class OrderPlacedEvent {
   private final Long orderId;

   public OrderPlacedEvent(Long orderId) {
      this.orderId = orderId;
   }

   public Long getOrderId() {
      return orderId;
   }
}
