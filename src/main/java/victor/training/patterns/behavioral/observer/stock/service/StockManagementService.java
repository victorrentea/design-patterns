package victor.training.patterns.behavioral.observer.stock.service;

import org.springframework.stereotype.Service;

@Service
public class StockManagementService {
   public void checkStock(long orderId) {
      System.out.println("Checking stock for products in order " + orderId);
      System.out.println("If something goes wrong - throw an exception");
   }
}
