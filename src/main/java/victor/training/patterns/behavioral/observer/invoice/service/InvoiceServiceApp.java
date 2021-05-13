package victor.training.patterns.behavioral.observer.invoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class InvoiceServiceApp {
   public static void main(String[] args) {
      SpringApplication.run(InvoiceServiceApp.class, args);
   }
//   @EventListener
////   @Order(2)
//   public void generateInvoice(OrderWasInStockEvent event) {
//      log.debug("Generating invoice for order id: " + event.getOrderId());
//      // TODO what if...
//      // throw new RuntimeException("thrown from generate invoice");
//   }
}
