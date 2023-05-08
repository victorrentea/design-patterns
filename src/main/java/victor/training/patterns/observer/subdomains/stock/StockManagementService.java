package victor.training.patterns.observer.subdomains.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockManagementService {
    public void checkStock(long orderId) {
        log.info("STOCK: Checking stock for products in order " + orderId);
    }
}
