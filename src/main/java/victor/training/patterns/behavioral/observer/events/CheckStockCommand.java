package victor.training.patterns.behavioral.observer.events;

import lombok.Value;

@Value
public class CheckStockCommand { // o intrebare cerere
   long orderId;
}
