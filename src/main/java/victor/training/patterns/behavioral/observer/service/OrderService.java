package victor.training.patterns.behavioral.observer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
