package victor.training.patterns.behavioral.observer.events;

public class StockReservedEvent {
   private final Long orderId;

   public StockReservedEvent(Long orderId) {
      this.orderId = orderId;
   }

   public Long getOrderId() {
      return orderId;
   }
}
