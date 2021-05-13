package victor.training.patterns.behavioral.observer.events;

public class OrderWasInStockEvent {
   private final Long orderId;

   public OrderWasInStockEvent(Long orderId) {
      this.orderId = orderId;
   }

   public Long getOrderId() {
      return orderId;
   }
}
