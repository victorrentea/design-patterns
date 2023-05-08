package victor.training.patterns.observer.subdomains.events;

import lombok.Value;

@Value
public class OrderPlacedEvent {
  long orderId;
}
