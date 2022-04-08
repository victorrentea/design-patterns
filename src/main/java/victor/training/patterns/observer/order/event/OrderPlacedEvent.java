package victor.training.patterns.observer.order.event;

import lombok.Data;

@Data
public class OrderPlacedEvent {
   private final long orderId;
}
