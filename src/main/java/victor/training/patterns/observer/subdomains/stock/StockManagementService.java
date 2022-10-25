package victor.training.patterns.observer.subdomains.stock;

import org.springframework.stereotype.Service;

@Service
public class StockManagementService {
    public void checkStock(long orderId) {
        System.out.println("STOCK: Checking stock for products in order " + orderId);
    }
}
