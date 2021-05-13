package victor.training.patterns.behavioral.observer.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.invoice.service.InvoiceService;
import victor.training.patterns.behavioral.observer.stock.service.StockManagementService;

@Service
public class OrderService {
   @Autowired
   private StockManagementService stockManagementService;
   @Autowired
   private InvoiceService invoiceService;

   public void placeOrder(Long orderId) {
      System.out.println("Halo!");
      stockManagementService.checkStock(orderId);
      invoiceService.generateInvoice(orderId);
   }
}
