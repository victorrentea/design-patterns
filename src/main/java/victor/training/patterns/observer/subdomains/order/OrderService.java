package victor.training.patterns.observer.subdomains.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.stock.StockManagementService;

@Service
public class OrderService {
    @Autowired
    private StockManagementService stockManagementService;

    public void placeOrder(Long orderId) {
        System.out.println("Halo!");
        stockManagementService.checkStock(orderId);
        // TODO call invoicing too
    }
}
