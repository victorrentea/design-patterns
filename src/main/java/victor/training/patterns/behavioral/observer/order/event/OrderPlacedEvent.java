package victor.training.patterns.behavioral.observer.order.event;

import lombok.Value;

@Value
public class OrderPlacedEvent {
   long orderId;
}
