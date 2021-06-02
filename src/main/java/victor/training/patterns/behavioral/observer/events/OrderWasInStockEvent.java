package victor.training.patterns.behavioral.observer.events;

public class OrderWasInStockEvent {
   private final long orderId;

   public OrderWasInStockEvent(long orderId) {
      this.orderId = orderId;
   }

   public long getOrderId() {
      return orderId;
   }
}
