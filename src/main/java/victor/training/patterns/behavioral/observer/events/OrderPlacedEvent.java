package victor.training.patterns.behavioral.observer.events;

import lombok.Value;

@Value
public class OrderPlacedEvent {
   Long orderId;
}
