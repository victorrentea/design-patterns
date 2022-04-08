package victor.training.patterns.observer.stock.event;

import lombok.Data;

@Data
public class StockReservedEvent {
   private final long orderId; //
}
