package victor.training.patterns.behavioral.observer.events;

public class OrderPlacedEvent {
   private final long orderId;

   public OrderPlacedEvent(long orderId) {
      this.orderId = orderId;
   }

   public long getOrderId() {
      return orderId;
   }
}
