package victor.training.patterns.solid;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Liskov {
   public static void main(String[] args) {
      poorClient(new OrderLine(new Product("Chair", 3), 2));
      poorClient(new BulkOrderLine(new Product("Beans", 10), 5));
   }

   public static void poorClient(OrderLine orderLine) {
      log.debug("Client bought {} x {}", orderLine.getItemCount(), orderLine.getProduct().getName());
   }
}

@Getter
@Setter
class OrderLine {
   protected Product product;
   private int itemCount;
   // Other reusable things
   private Long orderId;
   private List<Coupon> appliedCoupons = new ArrayList<>();

   // stuff that it's not correct anymore in subclasses
   public OrderLine() {
   }

   public OrderLine(Product product, int itemCount) {
      this.product = product;
      this.itemCount = itemCount;
   }

   public double computePrice() {
      return product.getPrice() * itemCount;
   }
}

@Getter
@Setter
class BulkOrderLine extends OrderLine {
   private double weightKg;

   public BulkOrderLine(Product product, double weightKg) {
      this.product = product;
      this.weightKg = weightKg;
   }

   @Override
   public double computePrice() {
      return getProduct().getPrice() * weightKg;
   }
}

@Data
class Product {
   private double price;
   private String name;

   public Product(String name, double price) {
      this.price = price;
      this.name = name;
   }
}

class Coupon {
   // complexity
}