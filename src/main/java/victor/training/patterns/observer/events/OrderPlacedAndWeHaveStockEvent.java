package victor.training.patterns.observer.events;

import lombok.Value;

@Value
public class OrderPlacedAndWeHaveStockEvent {
    long orderId;
}
