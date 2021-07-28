package victor.training.patterns.behavioral.observer.events;

import lombok.Value;

@Value
//@Data
public class OrderPlacedEvent {
   long orderId;
}

