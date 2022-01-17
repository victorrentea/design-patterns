package victor.training.patterns.behavioral.observer.events;

public class StockReservedForOrderEvent {
   private final long orderId;

   public StockReservedForOrderEvent(long orderId) {
      this.orderId = orderId;
   }

   public long getOrderId() {
      return orderId;
   }
}
