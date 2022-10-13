package victor.training.patterns.observer.events;

import lombok.Value;

@Value
public class OrderPlacedEvent {
    long orderId;
}
