package victor.training.patterns.behavioral.observer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.invoice.InvoiceService;
import victor.training.patterns.behavioral.observer.stock.StockManagementService;

@Service
public class OrderService {
   @Autowired
   private StockManagementService stockManagementService;
   @Autowired
   private InvoiceService invoiceService;

   public void placeOrder(Long orderId) {
      System.out.println("Persist order!");
      stockManagementService.checkStock(orderId);
      invoiceService.generateInvoice(orderId);
   }
}
