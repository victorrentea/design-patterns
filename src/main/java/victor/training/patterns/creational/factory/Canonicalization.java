package victor.training.patterns.creational.factory;

public class Canonicalization {


   public static void main(String[] args) {
      OrderId o1 = OrderId.of(13);

      OrderId o2 = OrderId.of(13);
   }
}


class OrderId {
   private final long id;

   private OrderId(long id) {
      this.id = id;
   }

   public static OrderId of(long id) {
      // using a WeakHashMap reuse existing instances in memory to avoid allocating 2 OrderId with the same id inside
      return null;
   }

   public long getId() {
      return id;
   }
}