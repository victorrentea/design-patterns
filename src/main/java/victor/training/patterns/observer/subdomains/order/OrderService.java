package victor.training.patterns.observer.subdomains.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.observer.subdomains.stock.StockManagementService;

@Service
public class OrderService {
    @Autowired
    private StockManagementService stockManagementService;

    public void placeOrder() {
        System.out.println("Saving the order in the DB!");
        Long orderId = 13L; // = pretend the inserted PK

        stockManagementService.checkStock(orderId);
        // TODO call invoicing too
    }
}
