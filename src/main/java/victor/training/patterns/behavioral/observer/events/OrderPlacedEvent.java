package victor.training.patterns.behavioral.observer.events;

import lombok.Value;

@Value
public class OrderPlacedEvent { // message on queue
   long orderId;
}
