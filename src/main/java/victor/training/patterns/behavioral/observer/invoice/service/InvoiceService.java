package victor.training.patterns.behavioral.observer.invoice.service;

import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
   public void generateInvoice(long orderId) {
      System.out.println("Generating invoice for order id: " + orderId);
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
